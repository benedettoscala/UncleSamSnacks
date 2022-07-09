package control;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.ProdottoBean;
import model.ProdottoModel;

/**
 * Servlet implementation class InserimentoProdottoControl
 */
@WebServlet("/InserimentoProdottoControl")
public class InserimentoProdottoControl extends HttpServlet {
	
	private static final String UPLOAD_DIRECTORY = "images";
	private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoProdottoControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // controlla se la request contiene dati
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("La richiesta non ha dati");
            writer.flush();
            return;
        }
        ProdottoModel prMdl = new ProdottoModel();
		ProdottoBean prodotto = new ProdottoBean(); 
        
        // configura le impostazioni di upload
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
         
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
         
        // costruisci il path per contenere la directory
        String uploadPath = getServletContext().getRealPath("")
            + File.separator + UPLOAD_DIRECTORY;
        // crea la directory se non esiste
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
         
        try {
        	
            // analizza la request 
            List<FileItem> formItems = upload.parseRequest(request);
            Iterator<FileItem> iter = formItems.iterator();
            
            // itera su tutti i campi del form
            while (iter.hasNext()) {
            	
                FileItem item = (FileItem) iter.next();
                
                
                if(item.isFormField() && item!=null)
                {
                	switch(item.getFieldName())
                	{
                	case "nome":
                		prodotto.setNome(item.getString());
                		break;
                	case "prezzo":
                		prodotto.setPrezzo(Double.parseDouble(item.getString()));
                		break;
                	case "descrizione":
                		prodotto.setDescrizione(item.getString());
                		break;
                	case "quantita":
                		prodotto.setQuantita(Integer.parseInt(item.getString()));
                		break;
                	case "sconto":
                		prodotto.setSconto(Integer.parseInt(item.getString()));
                		break;
                	case "categoria":
                		prodotto.setCategoria(item.getString());
                		break;
                	case "azienda":
                		prodotto.setAzienda(item.getString());
                		break;
                	case "IVA":
                		prodotto.setIVA(Integer.parseInt(item.getString()));
                		break;
                	}
      
                }
             // processa ogni campo che non è form field
                if (!item.isFormField()) {
                    String fileName = new File(prodotto.getNome().replace(" ", "")).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    prodotto.setUrlImmagine(UPLOAD_DIRECTORY + File.separator + fileName);
                    File storeFile = new File(filePath);
                     
                    // saves the file on disk
                    item.write(storeFile);
                }
                
            }
      
            prMdl.inserisciProdotto(prodotto);
            request.setAttribute("titolo", "Inserimento Prodotto");
            request.setAttribute("messaggio", "Prodotto inserito con successo!");
        } catch (Exception ex) {
            request.setAttribute("message", "C'è stato un errore: " + ex.getMessage());
        }
        getServletContext().getRequestDispatcher("/Messaggio.jsp").forward(request, response);

	}

}
