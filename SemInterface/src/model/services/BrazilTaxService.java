package model.services;

public class BrazilTaxService {
	//double tipo primitivo  pq sempre vai retornar um valor no argumento.
	//Nao tera valor nulo.
	public double tax(double amount) {
		if(amount <=100.0) {
			return amount*0.2;
		}else {
			return amount*0.15;
		}
	}

}
