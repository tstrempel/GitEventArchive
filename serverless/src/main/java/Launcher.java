import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Launcher {
    public static void main(String[] args) {
        HttpResponse<String> response = Unirest
                .get("http://localhost:8080/t/GitEventArchive/requesthandler")
                .asString();


        System.out.println(response.getBody());
    }
}
