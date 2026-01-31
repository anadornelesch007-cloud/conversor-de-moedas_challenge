import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConsultaMoeda consulta = new ConsultaMoeda();
        int opcao = 0;

        while (opcao != 7) {
            System.out.println("************************************************");
            System.out.println("Seja bem-vindo(a) ao Conversor de Moeda! \n");
            System.out.println("1) Dólar (USD) =>> Iene Japonês (JPY)");
            System.out.println("2) Iene Japonês (JPY) =>> Dólar (USD)");
            System.out.println("3) Dólar (USD) =>> Dólar Australiano (AUD)");
            System.out.println("4) Dólar Australiano (AUD) =>> Dólar (USD)");
            System.out.println("5) Dólar (USD) =>> Euro (EUR) [Itália]");
            System.out.println("6) Euro (EUR) =>> Real Brasileiro (BRL)");
            System.out.println("7) Sair");
            System.out.println("Escolha uma opção válida:");
            System.out.println("************************************************");

            opcao = leitura.nextInt();

            if (opcao == 7) {
                System.out.println("Finalizando o programa...");
                break;
            }

            if (opcao < 1 || opcao > 7) {
                System.out.println("Opção inválida! Tente novamente.");
                continue;
            }

            System.out.println("Digite o valor que deseja converter:");
            double valorParaConverter = leitura.nextDouble();

            try {
                switch (opcao) {
                    case 1 -> exibirConversao("USD", "JPY", valorParaConverter, consulta);
                    case 2 -> exibirConversao("JPY", "USD", valorParaConverter, consulta);
                    case 3 -> exibirConversao("USD", "AUD", valorParaConverter, consulta);
                    case 4 -> exibirConversao("AUD", "USD", valorParaConverter, consulta);
                    case 5 -> exibirConversao("USD", "EUR", valorParaConverter, consulta);
                    case 6 -> exibirConversao("EUR", "BRL", valorParaConverter, consulta);
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar conversão: " + e.getMessage());
            }
        }
    }

    public static void exibirConversao(String base, String destino, double valor, ConsultaMoeda consulta) {

        String json = consulta.buscaMoeda(base);
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();


        JsonObject taxas = jsonObject.get("conversion_rates").getAsJsonObject();
        double taxa = taxas.get(destino).getAsDouble();


        double resultado = valor * taxa;

        System.out.println("O valor " + valor + " [" + base + "] equivale a: =>>> "
                + resultado + " [" + destino + "]\n");
    }

}
