//package cerveja;
//
//import static org.junit.Assert.assertEquals;
//
//import java.math.BigDecimal;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.starking.cerveja.model.Cerveja;
//import com.starking.cerveja.session.TabelaItensSession;
//
//public class TabelaItensVendaTest {
//
//	private TabelaItensSession tabelaItensVenda;
//	
//	@Before
//	public void setUp() {
//		this.tabelaItensVenda = new TabelaItensSession();
//	}
//	
//	@Test
//	public void deveCalcularValorTotalSemItens(String uuid) throws Exception {
//		assertEquals(BigDecimal.ZERO, tabelaItensVenda.getValorTotal(uuid));
//	}
//	
//	@Test
//	public void deveCalcularValorTotalComUmItem(String uuid) throws Exception {
//		Cerveja cerveja = new Cerveja();
//		BigDecimal valor = new BigDecimal("8.90");
//		cerveja.setValor(valor);
//		
//		tabelaItensVenda.adicionarItem(uuid, cerveja, 1);
//		
//		assertEquals(valor, tabelaItensVenda.getValorTotal(uuid));
//	}
//	
//	@Test
//	public void deveCalcularValorTotalComVariosItens(String uuid) throws Exception {
//		Cerveja c1 = new Cerveja();
//		c1.setId(1L);
//		BigDecimal v1 = new BigDecimal("8.90");
//		c1.setValor(v1);
//		
//		Cerveja c2 = new Cerveja();
//		c2.setId(2L);
//		BigDecimal v2 = new BigDecimal("4.99");
//		c2.setValor(v2);
//		
//		tabelaItensVenda.adicionarItem(uuid, c1, 1);
//		tabelaItensVenda.adicionarItem(uuid, c2, 2);
//		
//		assertEquals(new BigDecimal("18.88"), tabelaItensVenda.getValorTotal(uuid));
//	}
//	
//	@Test
//	public void deveManterTamanhoDaListaParaMesmasCervejas() throws Exception {
//		Cerveja c1 = new Cerveja();
//		c1.setId(1L);
//		c1.setValor(new BigDecimal("4.50"));
//		
//		tabelaItensVenda.adicionarItem(c1, 1);
//		tabelaItensVenda.adicionarItem(c1, 1);
//		
//		assertEquals(1, tabelaItensVenda.total());
//		assertEquals(new BigDecimal("9.00"), tabelaItensVenda.getValorTotal());
//	}
//	
//	@Test
//	public void deveAlterarQuantidadeDoItem() throws Exception {
//		Cerveja c1 = new Cerveja();
//		c1.setId(1L);
//		c1.setValor(new BigDecimal("4.50"));
//		
//		tabelaItensVenda.adicionarItem(c1, 1);
//		tabelaItensVenda.alterarQuantidadeItens(c1, 3);
//		
//		assertEquals(1, tabelaItensVenda.total());
//		assertEquals(new BigDecimal("13.50"), tabelaItensVenda.getValorTotal());
//	}
//	
//	@Test
//	public void deveExcluirItem() throws Exception {
//		Cerveja c1 = new Cerveja();
//		c1.setId(1L);
//		c1.setValor(new BigDecimal("8.90"));
//		
//		Cerveja c2 = new Cerveja();
//		c2.setId(2L);
//		c2.setValor(new BigDecimal("4.99"));
//		
//		Cerveja c3 = new Cerveja();
//		c3.setId(3L);
//		c3.setValor(new BigDecimal("2.00"));
//		
//		tabelaItensVenda.adicionarItem(c1, 1);
//		tabelaItensVenda.adicionarItem(c2, 2);
//		tabelaItensVenda.adicionarItem(c3, 1);
//		
//		tabelaItensVenda.excluirItem(c2);
//		
//		assertEquals(2, tabelaItensVenda.total());
//		assertEquals(new BigDecimal("10.90"), tabelaItensVenda.getValorTotal());
//	}
//}