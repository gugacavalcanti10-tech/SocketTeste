import javax.swing.*;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("127.0.0.1", 12345); //hostname + porta do servidor

            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

            /*
                1-) getInputStream faz referencia ao retorno
                do canal bruto de entrada do socket, do tipo inputStream. É por essa
                Stream que acontece a entrada dos bytes.

                2-)ObjectInputStream é o envoltro dos bytes

                3-)entrada é o ObjectInputStream conectado ao socket do servidor
             */

            Date dataAtual = (Date)entrada.readObject(); // readObject bloqueia a execução das proximas linha de codigo
            // até que chegue a quantidade de informações desejadas, enquanto isso, ele espera, ate a condição ser atendida

            /*
            1-) ReadObject(); : de maneira isolada faz referncia a uma chamada obsoleta de um objeto
            mas como na classe de Servidor ja foi previamente definido, faz referencia ao comando "Date"

            2-)dataAtual guarda o valor da cast, de  tipo Date
             */

            JOptionPane.showMessageDialog(null, "data recebecida do servidor: " + dataAtual.toString());

            /*
            1-) JoptionPane é um método estatico que cria um bloco de dialogo simples!!!

            2-).showMenssageDialog é outro método estatico dentro de JoptionPane, no qual cria uma interação
            de duas possibilidades(clicando "OK" ou no "X")

            3-) Null dentro do primeiro parametro de showMenssageDialog é usado para centralizar a janela caso tenha uma anterior
            a ela, mas nesse caso não existe, por isso o valor e nulo.

            Logo em seguida a concatenação de uma String, um texto literal, com a chamada de um método, que nesse
            caso é o ToString do dataAtual, do tipo Date, que consequentimente vai converter a data em um texto
            legível.

            OBSERVAÇÃO: O "To.String" poderia ser omitido, porque a concatenação já realiza a chamada automaticamente.
             */
            entrada.close();
            cliente.close();
            System.out.println("conexão enterrada com sucesso!");
            /*
            entrada.close(); -> vai liberar os recursos internos usados.
                Ele também fecha automaticamente o inputStream bruto do socket que estava decorando por baixo

            cliente.close(); -> fecha a conexão por completo, incluindo do cliente com o socket.

            incluindo:
            o canal de entrada e o canal de saída, mesmo que não tenha usado nesse cliente em específico, e
            libera de fato o socket á nível de SO, então, a conexão TCP é finalizada de maneira correta.

            A print é so para deixar uma String literal explícita que houve o encerramento da conexão entre
            socket e cliente de maneira natural.
             */

        } catch (Exception e) {
            System.out.println("ERRO");
        }
    }
}
