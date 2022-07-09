package control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * Servlet implementation class ProdottoModificatoControl
 */
@WebServlet("/ProdottoModificatoControl")
public class ProdottoModificatoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "images";
	private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdottoModificatoControl() {
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
		// checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Request does not contain upload data");
            writer.flush();
            return;
        }
        ProdottoModel prMdl = new ProdottoModel();
		ProdottoBean prodotto = new ProdottoBean(); 
        
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
         
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
         
        // constructs the directory path to store upload file
        String uploadPath = getServletContext().getRealPath("")
            + File.separator + UPLOAD_DIRECTORY;
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
         
        try {
        	
            // parses the request's content to extract file data
            List<FileItem> formItems = upload.parseRequest(request);
            Iterator<FileItem> iter = formItems.iterator();
            
            // iterates over form's fields
            while (iter.hasNext()) {
            	
                FileItem item = (FileItem) iter.next();
                
                // processes only fields that are not form fields
                if(item.isFormField() && item!=null)
                {
                	System.out.println(item.getFieldName());
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
                	case "idProdotto":
                		prodotto.setIdProdotto(Integer.parseInt(item.getString()));
                		break;
                	case "IVA":
                		prodotto.setIVA(Integer.parseInt(item.getString()));
                		break;
                	}
      
                }
                
                if (!item.isFormField()&&item.getSize()!=0) {
                	
                    String fileName = new File(prodotto.getNome().replace(" ", "")).getName();
                    String filePath = uploadPath + File.separator + fileName+".png";
                    prodotto.setUrlImmagine(UPLOAD_DIRECTORY + File.separator + fileName+".png");
                    File storeFile = new File(filePath);
                    System.out.println(item.getSize());
                    // saves the file on disk
                    item.write(storeFile);
                }
                
            }
            String fileName = new File(prodotto.getNome().replace(" ", "")).getName();
            String filePath = uploadPath + File.separator + fileName+".png";
            prodotto.setUrlImmagine(UPLOAD_DIRECTORY + File.separator + fileName+".png");
            System.out.println(prodotto);
            prMdl.modificaProdotto(prodotto);
            request.setAttribute("titolo", "Modifica Prodotto");
            request.setAttribute("messaggio", "Prodotto Modificato Con Successo");
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
        getServletContext().getRequestDispatcher("/Messaggio.jsp").forward(request, response);

	}

}
