package br.com.fiap.bean;

import java.io.Serializable;





import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import br.com.fiap.dao.VendaDAO;
import br.com.fiap.dao.impl.VendaDAOImpl;
import br.com.fiap.singleton.EMFactorySingleton;

@ManagedBean
@ViewScoped
public class RelatorioBean implements Serializable {

	//Objeto que armazena as informações do gráfico de pizza
	private PieChartModel grafico;
	
	//Objeto que armazena as informações do gráfico de linha
	private LineChartModel graficoLinha;
	
	private VendaDAO dao;

	@PostConstruct
	private void init(){
		EntityManager em = EMFactorySingleton.getInstance().createEntityManager();
		dao = new VendaDAOImpl(em);
		
		//Inicializa os valores do gráfico
		grafico = new PieChartModel();
		long carne = dao.contarVenda("Pastel Carne");
		grafico.set("Carne", carne);
		
		long frango = dao.contarVenda("Pastel Frango");
		grafico.set("Frango", frango);
		
		long pizza = dao.contarVenda("Pastel Pizza");
		grafico.set("Pizza", pizza);
		
		long queijo = dao.contarVenda("Pastel Queijo");
		grafico.set("Queijo", queijo);
		
		grafico.setLegendPosition("e");
		grafico.setShowDataLabels(true);
		
		//Inicializar as informações do gráfico de linha
		graficoLinha = new LineChartModel();
		graficoLinha.setLegendPosition("w");
		graficoLinha.setTitle("Vendas por Data");
		graficoLinha.getAxes().put(AxisType.X, new CategoryAxis("Mês"));
		
		//Informações de uma linha do gráfico
		LineChartSeries carne2 = new LineChartSeries();
		carne2.setLabel("Pastel de Carne");
		
		LineChartSeries frango2 = new LineChartSeries();
		frango2.setLabel("Pastel de Frango");
		
		LineChartSeries queijo2 = new LineChartSeries();
		queijo2.setLabel("Pastel de Queijo");
		
		LineChartSeries pizza2 = new LineChartSeries();
		pizza2.setLabel("Pastel de Pizza");
		
		for (int i=1;i<=12;i++){
			carne2.set(i, dao.contarVenda("Pastel Carne", i));
			frango2.set(i, dao.contarVenda("Pastel Frango", i));
			queijo2.set(i, dao.contarVenda("Pastel Queijo", i));
			pizza2.set(i, dao.contarVenda("Pastel Pizza", i));
		}
		
		graficoLinha.addSeries(carne2);
		graficoLinha.addSeries(queijo2);
		graficoLinha.addSeries(frango2);
		graficoLinha.addSeries(pizza2);
	}
	
	public PieChartModel getGrafico() {
		return grafico;
	}

	public LineChartModel getGraficoLinha() {
		return graficoLinha;
	}

}










