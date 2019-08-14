package BatalhaNavalLuiza;

import java.awt.Toolkit;
import java.util.Random;
import javax.swing.JOptionPane;

public class BatalhaNavalLuizaMaria {

    public static void main(String[] args) {
        do {
            iniciarJogo();
        } while (JOptionPane.showConfirmDialog(null, "Você quer continuar?", "Batalha Naval", JOptionPane.OK_CANCEL_OPTION) == 0);

    }

    public static void iniciarJogo() {

        String regras = ".::Regras::.\n\n";
        regras += "\n>Batalha naval é um jogo no qual o jogador tem de adivinhar em que posições estão os navios alvos.\n";
        regras += "\n>Este jogo é composto de 4 alvos dispostos aleatoriamente no tabuleiro(20X20), cada um ocupando 3 posições cada.\n";
        regras += "\n>Seu objetivo é acertar os alvos em menos tentativas possíveis.\n";
        regras += "\n>>>>Boa Sorte!<<<<\n";
        JOptionPane.showMessageDialog(null, regras);

        //Criar tabuleiro com zeros
        Integer[][] tabuleiro = new Integer[20][20];
        for (int linhas = 0; linhas < 20; linhas++) {
            for (int colunas = 0; colunas < 20; colunas++) {
                tabuleiro[linhas][colunas] = 0;
            }
        }
        //Criar as coordenadas aleatórias na horizontal (ALVO)
        Integer qtAlvos = 0;
        while (qtAlvos < 2) {
            Random r = new Random();
            Integer linhaAleatoria = r.nextInt(20);
            Integer colunaAleatoria = r.nextInt(18);
            if (tabuleiro[linhaAleatoria][colunaAleatoria] == 1
                    || tabuleiro[linhaAleatoria][colunaAleatoria + 1] == 1
                    || tabuleiro[linhaAleatoria][colunaAleatoria] + 2 == 1) {

                System.out.println("Alvo ja existe " + linhaAleatoria + ", " + colunaAleatoria);
                continue;
            }

            qtAlvos++;
            tabuleiro[linhaAleatoria][colunaAleatoria] = 1;
            tabuleiro[linhaAleatoria][colunaAleatoria + 1] = 1;
            tabuleiro[linhaAleatoria][colunaAleatoria + 2] = 1;
            System.out.println("Alvo " + linhaAleatoria + ", " + colunaAleatoria);
        }

        //Criar as coordenadas aleatórias na vertical(ALVO)
        Integer qtAlvos2 = 0;
        while (qtAlvos2 < 2) {
            Random random = new Random();
            Integer linhaAleatoriaVertical = random.nextInt(20);
            Integer colunaAleatoriaVertical = random.nextInt(18);
            if (tabuleiro[linhaAleatoriaVertical][colunaAleatoriaVertical] == 1
                    || tabuleiro[linhaAleatoriaVertical + 1][colunaAleatoriaVertical] == 1
                    || tabuleiro[linhaAleatoriaVertical + 2][colunaAleatoriaVertical] == 1) {
                System.out.println("Alvo ja existe " + linhaAleatoriaVertical + ", " + colunaAleatoriaVertical);
                continue;
            }
            qtAlvos2++;
            tabuleiro[linhaAleatoriaVertical][colunaAleatoriaVertical] = 1;
            tabuleiro[linhaAleatoriaVertical + 1][colunaAleatoriaVertical] = 1;
            tabuleiro[linhaAleatoriaVertical + 2][colunaAleatoriaVertical] = 1;
            System.out.println("Alvo " + linhaAleatoriaVertical + ", " + colunaAleatoriaVertical);

        }

        Integer acerto = 0;
        Integer tentativas = 0;
        do {
            imprimirTabuleiro(tabuleiro);
            try {
                String solicitaL = JOptionPane.showInputDialog("Digite linha do tiro");
                Integer linhaUsuario = new Integer(solicitaL);
                linhaUsuario = linhaUsuario - 1;
                String solicitaC = JOptionPane.showInputDialog("Digite coluna do tiro");
                Integer colunaUsuario = new Integer(solicitaC);
                colunaUsuario = colunaUsuario - 1;

                if (conferirTiro(linhaUsuario, colunaUsuario, tabuleiro)) {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Tiro certo");
                    acerto++;
                    tentativas++;
                    tabuleiro[linhaUsuario][colunaUsuario] = 0;
                    System.out.println("");

                } else {
                    JOptionPane.showMessageDialog(null, "Água");
                    tentativas++;

                }

            } catch (Exception e) {
                String msg = e.getMessage();
                JOptionPane.showMessageDialog(null, "Digite novamente!\n" + msg);
            }

        } while (acerto < 12);
        imprimirTabuleiro(tabuleiro);
        Toolkit.getDefaultToolkit().beep();
        String mensagem = "VOCÊ CONSEGUIU, CAPITÃO! O TESOURO É SEU!\n\n";
        mensagem += "Placar final:\n\n";
        mensagem += "\nQuantidade de tiros: " + tentativas;
        mensagem += "\nQuantidade de acertos: " + acerto;
        JOptionPane.showMessageDialog(null, mensagem);

    }

    public static boolean conferirTiro(Integer linhaUsuario, Integer colunaUsuario, Integer[][] tabuleiro) {

        if (tabuleiro[linhaUsuario][colunaUsuario] == 1) {
            return true;

        } else {
            return false;
        }

    }

    public static void imprimirTabuleiro(Integer[][] tabuleiro) {
        System.out.println("\n\n:: TABULEIRO ::");
        for (int linha = 0; linha < 20; linha++) {
            for (int coluna = 0; coluna < 20; coluna++) {
                System.out.print(tabuleiro[linha][coluna] + "  ");
            }
            System.out.println("");

        }
    }

}
