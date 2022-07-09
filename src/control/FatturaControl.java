package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import bean.IndirizzoBean;
import bean.InserimentoBean;
import bean.OrdineBean;
import bean.PagamentoBean;
import bean.UtenteBean;
import model.IndirizzoModel;
import model.InserimentoModel;
import model.OrdineModel;
import model.PagamentoModel;
import model.UtenteModel;

/**
 * Servlet implementation class FatturaControl
 */
@WebServlet("/FatturaControl")
public class FatturaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FatturaControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		// File name
        String pdfName = "fattura"+ id+".pdf";
        OrdineModel ordMdl = new OrdineModel();
        OrdineBean ordine = null;
		try {
			ordine = ordMdl.restituisciOrdine(Integer.parseInt(id));
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println(ordine);
        UtenteBean usr = (UtenteBean)request.getSession().getAttribute("user");
        if(usr==null||!(usr.getUsername().equals(ordine.getUtente())||usr.getAutorizzazione().equals("Amministratore"))) {
        	System.out.println(usr.getUsername()+ " " + ordine.getUtente());
        	response.sendRedirect("AccessoRifiutato.jsp");
        }else {
 		try 
		{
			createPdf(pdfName, Integer.parseInt(id),getServletContext().getRealPath(""));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		 PrintWriter out = response.getWriter();

	        // Set the content type and header of the response.
	        response.setContentType("application/msword");
	        response.setHeader("Content-Disposition",
	                           "attachment; filename=\""
	                               + pdfName + "\"");
	 
	        // Get FileInputStream object to identify the path
	        FileInputStream inputStream
	            = new FileInputStream(pdfName);
	 
	        // Loop through the document and write into the
	        // output.
	        int in;
	        while ((in = inputStream.read()) != -1) {
	            out.write(in);
	        }
	 
	        // Close FileInputStream and PrintWriter object
	        inputStream.close();
	        out.close();
	        new File(pdfName).delete();
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

	private static void createPdf(String file, Integer id, String path) throws IOException
	{
		UtenteModel utMdl = new UtenteModel();
		PagamentoModel pagMdl= new PagamentoModel();
		IndirizzoModel indMdl = new IndirizzoModel();
		OrdineModel ordMdl = new OrdineModel();
		InserimentoModel insMdl = new InserimentoModel();
		List<InserimentoBean> liste = null;
		OrdineBean ordine = null;
		UtenteBean utente= null;
		PagamentoBean pag = null;
		IndirizzoBean ind = null;
		try {
			ordine = ordMdl.restituisciOrdine(id);
			liste = insMdl.restiuisciInserimenti(id);
			utente = utMdl.retrieveUtente(ordine.getUtente());
			pag = pagMdl.recuperaPagamento(ordine.getIdPagamento());
			ind = indMdl.recuperaIndirizzo(ordine.getIdIndirizzo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Image image = new Image(ImageDataFactory.create(path+File.separator+"headerfooter/logosito.png")).setWidth(100f);
		
		PdfWriter writer = new PdfWriter(file);
		PdfDocument pdfDoc = new PdfDocument(writer);
		Document doc = new Document(pdfDoc);
		pdfDoc.setDefaultPageSize(PageSize.A4);

		
		float col = 280f;
		float columnWidth[] = {col, col};
		Table table = new Table(columnWidth);
		table.setBackgroundColor(new DeviceRgb(63, 169,219)).setFontColor(new DeviceRgb(255,255,255));
		table.addCell(new Cell().add(image)
					.setPadding(30f)
					.setFontSize(30f)
					.setBorder(Border.NO_BORDER)
				);
		table.addCell(new Cell().add(new Paragraph("Venduto da Uncle Sam Snacks\nVia isola che non c'è 70\n80030 Napoli\nItalia"))
				.setTextAlignment(TextAlignment.LEFT)
				.setPaddingTop(30f)
				.setPaddingBottom(30f)
				.setBorder(Border.NO_BORDER)
				);
		
		float colWidth[] = {80, 300, 100, 80};
		Table customerInfoTable = new Table(colWidth);
		customerInfoTable.addCell(new Cell(0,4).add(new Paragraph("Informazioni Cliente"))
				.setBold()
				.setBorder(Border.NO_BORDER)
				);
		customerInfoTable.addCell(new Cell().add(new Paragraph("Nome")).setBorder(Border.NO_BORDER));
		customerInfoTable.addCell(new Cell().add(new Paragraph(utente.getNome())).setBorder(Border.NO_BORDER));
		customerInfoTable.addCell(new Cell().add(new Paragraph("Cognome")).setBorder(Border.NO_BORDER));
		customerInfoTable.addCell(new Cell().add(new Paragraph(utente.getCognome())).setBorder(Border.NO_BORDER));
		customerInfoTable.addCell(new Cell().add(new Paragraph("Fattura no.")).setBorder(Border.NO_BORDER));
		customerInfoTable.addCell(new Cell().add(new Paragraph(ordine.getIdOrdine().toString())).setBorder(Border.NO_BORDER));
		customerInfoTable.addCell(new Cell().add(new Paragraph("Data Ordine")).setBorder(Border.NO_BORDER));
		customerInfoTable.addCell(new Cell().add(new Paragraph(ordine.getData().toString())).setBorder(Border.NO_BORDER));
		
		Table customerIndTable = new Table(colWidth);
		customerIndTable.addCell(new Cell(0,4).add(new Paragraph("Indirizzo Spedizione Cliente"))
				.setBold()
				.setBorder(Border.NO_BORDER)
				);
		customerIndTable.addCell(new Cell().add(new Paragraph("Citta")).setBorder(Border.NO_BORDER));
		customerIndTable.addCell(new Cell().add(new Paragraph(ind.getCitta())).setBorder(Border.NO_BORDER));
		customerIndTable.addCell(new Cell().add(new Paragraph("CAP")).setBorder(Border.NO_BORDER));
		customerIndTable.addCell(new Cell().add(new Paragraph(ind.getCap().toString())).setBorder(Border.NO_BORDER));
		customerIndTable.addCell(new Cell().add(new Paragraph("Via")).setBorder(Border.NO_BORDER));
		customerIndTable.addCell(new Cell().add(new Paragraph(ind.getVia())).setBorder(Border.NO_BORDER));
		customerIndTable.addCell(new Cell().add(new Paragraph("Numero Civico")).setBorder(Border.NO_BORDER));
		customerIndTable.addCell(new Cell().add(new Paragraph(ind.getNumerocivico().toString())).setBorder(Border.NO_BORDER));
		
		Table customerPagTable = new Table(colWidth);
		customerPagTable.addCell(new Cell(0,4).add(new Paragraph("Metodo Di Pagamento Cliente"))
				.setBold()
				.setBorder(Border.NO_BORDER)
				);
		customerPagTable.addCell(new Cell().add(new Paragraph("Numero Carta")).setBorder(Border.NO_BORDER));
		customerPagTable.addCell(new Cell().add(new Paragraph(pag.getNumeroCarta().replaceFirst("[0-9]{4}-[0-9]{4}-[0-9]{4}", "xxxx-xxxx-xxxx"))).setBorder(Border.NO_BORDER));
		customerPagTable.addCell(new Cell().add(new Paragraph("Nome Pagamento")).setBorder(Border.NO_BORDER));
		customerPagTable.addCell(new Cell().add(new Paragraph(pag.getNome())).setBorder(Border.NO_BORDER));
		customerPagTable.addCell(new Cell().add(new Paragraph("Cognome Pagamento")).setBorder(Border.NO_BORDER));
		customerPagTable.addCell(new Cell().add(new Paragraph(pag.getCognome())).setBorder(Border.NO_BORDER));
		customerPagTable.addCell(new Cell().add(new Paragraph("Data Scadenza")).setBorder(Border.NO_BORDER));
		customerPagTable.addCell(new Cell().add(new Paragraph(pag.getDataScadenza().toString().substring(0,7))).setBorder(Border.NO_BORDER));
		
		
		float itemInfoColWidth[] = {140,140,140,140,140};
		Table itemInfoTable = new Table(itemInfoColWidth);
		
		itemInfoTable.addCell(new Cell().add(new Paragraph("Immagine"))
				.setBackgroundColor(new DeviceRgb(63,169,219))
				.setFontColor(new DeviceRgb(255,255,255))
				);
		itemInfoTable.addCell(new Cell().add(new Paragraph("Nome Prodotto"))
				.setBackgroundColor(new DeviceRgb(63,169,219))
				.setFontColor(new DeviceRgb(255,255,255))
				);
		itemInfoTable.addCell(new Cell().add(new Paragraph("IVA"))
				.setBackgroundColor(new DeviceRgb(63,169,219))
				.setFontColor(new DeviceRgb(255,255,255))
				);
		itemInfoTable.addCell(new Cell().add(new Paragraph("Quantità"))
				.setBackgroundColor(new DeviceRgb(63,169,219))
				.setFontColor(new DeviceRgb(255,255,255))
				);
		
		itemInfoTable.addCell(new Cell().add(new Paragraph("Prezzo(con IVA prodotto singolo)"))
				.setBackgroundColor(new DeviceRgb(63,169,219))
				.setFontColor(new DeviceRgb(255,255,255))
				);
		
		
		
		for(InserimentoBean ins: liste)
		{
			Image imagePro = new Image(ImageDataFactory.create(path+File.separator+ins.getUrlImmagine())).setWidth(50f);
			String prezzo = (String.format("%,.2f", ins.getPrezzo()).replace(',', '.'));
			
			itemInfoTable.addCell(new Cell().add(imagePro).setTextAlignment(TextAlignment.CENTER));
			itemInfoTable.addCell(new Cell().add(new Paragraph(ins.getNomeProdotto())));
			itemInfoTable.addCell(new Cell().add(new Paragraph(ins.getIva().toString())));
			itemInfoTable.addCell(new Cell().add(new Paragraph(ins.getQuantita().toString()).setTextAlignment(TextAlignment.RIGHT)));
			itemInfoTable.addCell(new Cell().add(new Paragraph(prezzo).setTextAlignment(TextAlignment.RIGHT)));
		}
		
		itemInfoTable.addCell(new Cell().add(new Paragraph("Spedizione")));
		itemInfoTable.addCell(new Cell().add(new Paragraph()));
		itemInfoTable.addCell(new Cell().add(new Paragraph()));
		itemInfoTable.addCell(new Cell().add(new Paragraph().setTextAlignment(TextAlignment.RIGHT)));
		itemInfoTable.addCell(new Cell().add(new Paragraph("1,99").setTextAlignment(TextAlignment.RIGHT)));
		
		
		itemInfoTable.addCell(new Cell().add(new Paragraph(""))
				.setBorder(Border.NO_BORDER)
				.setBackgroundColor(new DeviceRgb(63,169,219))
				.setFontColor(new DeviceRgb(255,255,255))
				);
		itemInfoTable.addCell(new Cell().add(new Paragraph(""))
				.setBorder(Border.NO_BORDER)
				.setBackgroundColor(new DeviceRgb(63,169,219))
				.setFontColor(new DeviceRgb(255,255,255))
				);
		itemInfoTable.addCell(new Cell().add(new Paragraph(""))
				.setBorder(Border.NO_BORDER)
				.setBackgroundColor(new DeviceRgb(63,169,219))
				.setFontColor(new DeviceRgb(255,255,255))
				);
		itemInfoTable.addCell(new Cell().add(new Paragraph("Totale"))
				.setBorder(Border.NO_BORDER)
				.setBackgroundColor(new DeviceRgb(63,169,219))
				.setFontColor(new DeviceRgb(255,255,255))
				.setTextAlignment(TextAlignment.RIGHT)
				);
		itemInfoTable.addCell(new Cell().add(new Paragraph(ordine.getTotale().toString()))
				.setBorder(Border.NO_BORDER)
				.setBackgroundColor(new DeviceRgb(63,169,219))
				.setFontColor(new DeviceRgb(255,255,255))
				.setTextAlignment(TextAlignment.RIGHT)
				);
		doc.add(new Paragraph("Fattura").setFontSize(30f));
		doc.add(table);
		doc.add(new Paragraph("\n"));
		doc.add(customerInfoTable);
		doc.add(customerIndTable);
		doc.add(customerPagTable);
		doc.add(new Paragraph("\n"));
		doc.add(itemInfoTable);
		doc.close();
		
		
		
	}

}
