
package DashWebService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import DashDataBase.AccesoBD;

@WebService(serviceName = "DashboardWS")
public class DashboardWS {

    @WebMethod(operationName = "CONSUMO_TOTAL")
    public String[][] CONSUMO_TOTAL() {

       String resultado[][] = AccesoBD.CONSUMO_TOTAL();
       
        return resultado;
    }
}
