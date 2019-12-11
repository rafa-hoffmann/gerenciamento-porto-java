package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

public class Utilidades {
	public ArrayList<Navio> naviosCadastrados = new ArrayList<Navio>();
	public ArrayList<Caminhao> caminhoesCadastrados = new ArrayList<Caminhao>();

	public Queue<Navio> filaNavios = new LinkedList<Navio>();
	public Queue<Caminhao> filaCaminhoes = new LinkedList<Caminhao>();

	public ArrayList<Container> containers = new ArrayList<Container>();

	public void salvar() {
		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter("dados.txt", false));

			bw.write("---NAVIOSCADASTRADOS---\n");
			for (int i = 0; i < naviosCadastrados.size(); i++) {
				bw.write(naviosCadastrados.get(i).getMatricula() + "\n");
			}

			bw.write("---CAMINHOESCADASTRADOS---\n");
			for (int i = 0; i < caminhoesCadastrados.size(); i++) {
				bw.write(caminhoesCadastrados.get(i).getPlaca() + "\n");
			}

			bw.write("---FILANAVIOS---\n");
			for (Navio s : filaNavios) {
				bw.write(s.getMatricula() + "\n");
			}

			bw.write("---FILACAMINHOES---\n");
			for (Caminhao s : filaCaminhoes) {
				bw.write(s.getPlaca() + "\n");
			}

			bw.write("---CONTAINERS---\n");
			if (!containers.isEmpty()) {
				for (int i = 0; i < containers.size(); i++) {
					Container temp = containers.get(i);
					bw.write(String.format("%s\0%s\0%s\0%s\0%s\0%s\0\n", temp.getCod(), temp.getLoc(),
							temp.getDataEntrada(), temp.getEndRemetente(), temp.getRemetente(), temp.getDestino()));
				}
			}

			bw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar arquivo!");
		}
	}

	public void ler() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("dados.txt"));

			String linha = null;
			while ((linha = br.readLine()) != null) {
				try {
					while (!(linha = br.readLine()).equals("---CAMINHOESCADASTRADOS---")) {
						if (!linha.equals("---NAVIOSCADASTRADOS---"))
							naviosCadastrados.add(new Navio(linha));
					}
					while (!(linha = br.readLine()).equals("---FILANAVIOS---")) {
						if (!linha.equals("---CAMINHOESCADASTRADOS---"))
							caminhoesCadastrados.add(new Caminhao(linha));
					}
					while (!(linha = br.readLine()).equals("---FILACAMINHOES---")) {
						if (!linha.equals("---FILANAVIOS---"))
							filaNavios.add(new Navio(linha));
					}
					while (!(linha = br.readLine()).equals("---CONTAINERS---")) {
						if (!linha.equals("---FILACAMINHOES---"))
							filaCaminhoes.add(new Caminhao(linha));
					}
					while ((linha = br.readLine()) != null) {
						if (!linha.equals("---CONTAINERS---")) {
							String[] div = linha.split("\0");
							String cod = div[0];
							String loc = div[1];
							String dataEntrada = div[2];
							String endRemetente = div[3];
							String remetente = div[4];
							String destino = div[5];

							containers.add(new Container(cod, loc, dataEntrada, endRemetente, remetente, destino));
						}

					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "dados.txt corrompido!");
					System.exit(0);
				}
			}
			br.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivo de dados não encontrado!");
		}
	}
}
