package model.services;
import model.entities.CarRental;
import model.entities.Invoice;


public class RentalService {
	private Double pricePerDay;
	private Double pricePerHour;
	
	
	//associaco com brasilTxService. Sist.tm 2 pontos de alteracao caso 
	//mude a regra de imposto.Teria de trocar no Program e aqui 
	//o nome BrazilTaxService caso quissesse trocar para EUSTaxService pro exemplo.
//	private BrazilTaxService taxService;
	
	//Dependencia com a interface generica TaxService
	private TaxService taxService;


	public RentalService(Double pricePerDay, Double pricePerHour, TaxService taxService) {
		super();
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.taxService = taxService;
	}
	
	public void processInvoice(CarRental carRental) {
		//Usa as datas em mili segundos
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		//diferenca em horas.Diferenca em milisegundos/1000(para ter segunfos)
		//divide por 60 para minutos e /60 para horas.
		double hours = (double)(t2 - t1) / 1000 / 60 / 60;
		
		double basicPayment;
		if (hours <= 12.0) {
			//Math.ceil arredonda horas para cima
			basicPayment = pricePerHour * Math.ceil(hours);
		}
		else {
			basicPayment = pricePerDay * Math.ceil(hours / 24);
		}
// Calculo do imposto
		double tax = taxService.tax(basicPayment);
//Cria novo objeto de pagamento (new Invoice(basicPayment, tax)
//associa com o obejto de aluguel carRental
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}
	
	

}
