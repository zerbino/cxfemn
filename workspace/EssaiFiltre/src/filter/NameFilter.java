package filter;

import java.io.IOException;

import javax.ws.rs.BindingPriority;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import client.Personne;

@Provider
@ChangeNameBinding
//@BindingPriority(BindingPriority.HEADER_DECORATOR)
public class NameFilter implements ClientRequestFilter,
		ClientResponseFilter {


	public NameFilter() {
		
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.client.ClientResponseFilter#filter(javax.ws.rs.client.
	 * ClientRequestContext, javax.ws.rs.client.ClientResponseContext)
	 * L'argument requete n'est pas entièrement initialisé : par exemple, il est
	 * impossible de récupérer la méthode HTTP.
	 */
	public synchronized void filter(ClientRequestContext requete,
			ClientResponseContext reponse) throws IOException {

		System.out.println("coucou");
		Response resp =  (Response)requete.getEntity();
		Personne pers = (Personne) resp.getEntity();
		pers.setNom("Séguin-Henry");
		pers.setPrenom("Gregoire");
	}

	@Override
	public synchronized void filter(ClientRequestContext requete)
			throws IOException {
		System.out.println("cocu");

	}

}
