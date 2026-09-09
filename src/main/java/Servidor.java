import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Servidor {
    public static void main(String[] args) {

        try {

            //primeiro passo é instanciar servidor
            ServerSocket servidor = new ServerSocket(12345); // a porta é como uma frequencia de conexão

            System.out.println("servidor conectado a porta 12345");

            while (true) {

                // servidor precisa ficar rodando constantimente
                // isso possibilita a conexão com varios clientes, mas não são multiconexões
                Socket cliente = servidor.accept();// accept aceita a conexão do cliente e servidor
                System.out.println("Cliente conectado na rede: " + cliente.getInetAddress().getHostAddress());
                /*
                    1-) CLIENTE é o objeto

                    2-) cliente.getInetAdress():
                            é chamado sobre o objeto cliente. Esse metodo
                            retorna a InetAdress, que representa o endereço daquele cliente,
                            contem tanto o IP, quanto possivelmente o hostnamlee daquele cliente em especifico...

                    3-) (nome do servidor).getHostAdress:
                            é um metodo que faz retorno ao
                             endereço IP do servidor

                    4-) __________JUNÇÃO DOS DOIS!____________
                        Esse encadeamento é muito comum em Java: em vez de criar uma variável para cada
                        passo intermediário, você "encaixa" as chamadas de metodo
                 */

                ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());

                /*
                    1-) GetOutPtStream
                            retona o canal de saida bruta(OutputStream)... O que é o canal de saida bruta?
                            É a representação de mais baixo nivel possivel, ele apenas compreender comandos em bytes.
                            Logo, caso queira um numero inteiro, como por exemplo "42", ele precisa ser convertido
                            diretamente em byte

                    2-) new ObjectOutputStream:
                            Padrão Decorator: pega uma stream "basica" e adiciona outra stream com funcionalidade

                    3-) ObjectOutputStream:
                            Sem ele não seria possivel escrever bytes crus ou tipos primitivos de dados,
                            sendo preciso fazer a conversão manual daquelas informações (OutputStream).
                            Com ele, o Java cuida de toda serialização(transformação do dados em bytes)
                 */
                saida.flush(); // força o envio imedito do dado
                saida.writeObject(new Date()); // cria um novo objeto, que é data e hora atual do comando
                //pega o objeto e serializa e envia esses bytes atravez do stream, conectado ao cliente
                saida.close(); //fecha o ObjectOutputStream, liberando os recursos
                cliente.close();// fecha a conexão do cliente ao servidor

                /*
                OBSERVAÇÃO: O CAST SERÁ FEITO NO WRITEOBJECT(NEW DATE());
                E SERÁ CHAMADO NA CLASSE CLIENTE, DE MANEIRA ABSTRATA, MAS COM VALOR DE DATE
                 */


            }

        } catch (Exception e) {
            System.out.println("ERROR"); // aparece somente se for captado um erro na hora de rodar
            /*
            1-) catch:
                    é o responsavel para lídar com as excessões na hora da execução,
                    correspondentes ao try

            2-) Exception e:
                    É o tipo de erro, e o "e" é o nome da variavel que vai guardar o objeto da excecão que ocorreu
             */
        } finally {

        }
    }
}

