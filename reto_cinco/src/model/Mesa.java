package bar.interfaz;

import java.awt.List;
import java.util.ArrayList;

public class Mesa {
	
	//------------------------------------------------------------------------
	//							Atributos
	//------------------------------------------------------------------------
	
	/**
	 * Identificador de la mesa
	 */
	private int codigo;
	
	/**
	 * Pedidos de la mesa
	 */
	private ArrayList <Pedido> pedidos;
	
	/**
	 * Total ventas de la mesa
	 */
	private int totalVentasDia;
	
	//------------------------------------------------------------------------
	//							Constructor
	//------------------------------------------------------------------------
	
	/**
	 * Crea una mesa cargando información desde la base de datos.
	 * <b>post</b> Se inicializa el vector pedidos con los Pedidos que ha realizado la mesa.
	 * @throws Exception Si ocurre alguna falencia al cargar los datos.
	 */
	public Mesa(int codigoMesa, int unasVentas) throws Exception 
	{
		//Al momento de crear los objetos Mesa, la BD con AUTO_INCREMENT en esta columna me genera esos códigos sin repetición.
		this.codigo = codigoMesa; 
		this.totalVentasDia = unasVentas;
		//Lógica que se encarga de el acceso a la base de datos para crear los pedidos y añadirlos a este arreglo.
		pedidos = new ArrayList();	
	}
	
	//------------------------------------------------------------------------
	//							Métodos
	//------------------------------------------------------------------------
	
	/**
	 * Retorna el código de la mesa.
	 * @return Código de la mesa.
	 */	
	public int darCodigo()
	{
		return this.codigo;
	}
	
	/**
	 * Retorna el total de ventas de la mesa
	 * @return Total ventas de la mesa
	 */
	public int darTotalVentasDia(){
		return this.totalVentasDia;
	}
	
	/**
	 * Suma el total de una venta a las ventas diarias. <br>
	 * <b>post</b> El dinero de la venta se agrega al total de la venta diaria.
	 * @param dineroVenta
	 */
	public void agregarDineroVenta(int dineroVenta)
	{
		this.totalVentasDia += dineroVenta;
	}
	
	/**
	 * Retorna el arreglo de pedidos de la mesa. <br>
	 * @return El arreglo de pedidos de la mesa.
	 */
	public ArrayList<Pedido> darPedidos(){
		return this.pedidos;
	}
	
	/**
	 * Retorna el pedido.
	 * @param codigoPedido. Codigo del pedido.
	 * @return Pedido buscado.
	 * @throws Exception Si el código suministrado no corresponde a ningún pedido.
	 */
	public Pedido buscarPedido(int codigoPedido) throws Exception{
		
		for(Pedido pedido:pedidos)
		{
			if (pedido.darCodigo() == codigoPedido)
				{
					return pedido;
				}
		}
		
	}
	
	/**
	 * Agrega un nuevo pedido a la mesa.
	 * <b>pre</> El arreglo de pedidos debe estar inicializado.
	 * <b>post</b> Un nuevo pedido es agregado a la mesa.
	 */
	public void agregarPedido()
	{
		pedidos.add(new Pedido());
	}
	
	/**
	 * Elimina el pedido de la mesa. <br>
	 * <b>pre</b> El pedido no debe superar los 5 minutos después de que se creó. <br>
	 * <b>post</b> El pedido se elimina de el arreglo de pedidos.
	 * @param codigoPedido. Código del pedido a eliminar. codigoPedido != null.
	 * @throws Exception si el código del pedido no corresponde a ningún pedido.
	*/
	public void eliminarPedido(int codigoPedido) throws Exception
	{
		Pedido pedidoAEliminar = buscarPedido(codigoPedido);
		pedidos.remove(pedidoAEliminar);
		
	}
	
	
	/**
	 * Calcula el total a pagar de acuerdo a los pedidos realizados.
	 * @return Valor del total a pagar.
	 */
	public int calcularTotal()
	{
		int total = 0;
		for (Pedido pedido : pedidos )
		{
			total += pedido.darValorPedido();
		}
		
		return total;
	}
	
	/**
	 * Borra todos los pedidos que han sido ordenados por una mesa.
	 * <post> El arreglo pedidos ya no contiene ningún objeto Pedido.
	 */
	public void reiniciarMesa()
	{
		this.agregarDineroVenta(calcularTotal());
		pedidos.clear();
	}

}
