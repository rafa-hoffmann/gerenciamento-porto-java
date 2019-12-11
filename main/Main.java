package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.List;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuItem;

public class Main {

	Utilidades ut = new Utilidades();

	private JFrame frmSp;

	private JButton btnEnviarParaFila;
	private JButton btnDescarregarCaminhao;
	private JButton btnCarregarNavio;
	private JButton btnCarregarCaminhao;
	private JButton btnRmvNavio;
	private JButton btnAddNavio;
	private JButton btnRemoverCaminhao;
	private JButton btnAddCaminhao;
	private JButton btnEnviarCaminhao;
	private JButton btnDescarregarContiner;
	private JButton btnTerminarOperaoDe;
	private JButton btnTerminarOperaoDeCarga;
	private JButton btnRemoverDaFilaCaminhao;

	private JTable tbContainers;
	private JTable tbContainers2;

	private JPanel panelCarregar;
	private JPanel panelDescarregar;
	private JPanel panelCadastro;
	private JPanel panelMain;

	private JTextField tfCodigo;
	private JTextField tfLocal;
	private JTextField tfEndRemetente;
	private JTextField tfPlacaCaminhao;
	private JTextField tfMatriculaNavio;
	private JTextField tfDestinatario;

	private List listCaminhoesCadastrados;
	private List listNaviosCadastrados;

	private JLabel lblNavioNaFila;
	private JLabel lblCaminhaoNaFila;
	private JLabel lblNvsNaFila;
	private JLabel lblCmsNaFila;
	private JLabel lblCmsCadastrados;
	private JLabel tfDestino;
	private JLabel lblNvsCadastrados;
	private JLabel lblContinersNoPtio;

	private DefaultTableModel mContainers;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	private int tipo; // Atualiza tipo de transporte sendo descarregado.

	public static void main(String[] args) {
		File f1 = new File("dados.txt");
		try {
			f1.createNewFile();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro ao criar arquivo de dados!");
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Main window = new Main();
					window.frmSp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
		ut.ler();
		atualizarArrays();
		atualizarListas();
	}

	private void initialize() {
		frmSp = new JFrame();
		frmSp.setResizable(false);
		frmSp.setTitle("Automated Portuary System (APS)");
		frmSp.setBounds(100, 100, 1024, 650);
		frmSp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSp.getContentPane().setLayout(new CardLayout(0, 0));

		panelMain = new JPanel();
		frmSp.getContentPane().add(panelMain, "name_29575762680679");
		panelMain.setLayout(null);

		lblNvsNaFila = new JLabel("Navio na fila");
		lblNvsNaFila.setBounds(44, 11, 241, 14);
		panelMain.add(lblNvsNaFila);

		lblCmsNaFila = new JLabel("Caminh\u00E3o na fila");
		lblCmsNaFila.setBounds(575, 11, 241, 14);
		panelMain.add(lblCmsNaFila);

		JButton btnDescarregarNavio = new JButton("Descarregar");
		btnDescarregarNavio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ut.filaNavios.size() > 0) {
					tipo = 1;
					panelCarregar.setVisible(false);
					panelDescarregar.setVisible(true);
					panelCadastro.setVisible(false);
					panelMain.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum navio na fila!");
				}
			}
		});
		btnDescarregarNavio.setBounds(291, 77, 137, 23);
		panelMain.add(btnDescarregarNavio);

		btnDescarregarCaminhao = new JButton("Descarregar");
		btnDescarregarCaminhao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ut.filaCaminhoes.size() > 0) {
					tipo = 2;
					panelCarregar.setVisible(false);
					panelDescarregar.setVisible(true);
					panelCadastro.setVisible(false);
					panelMain.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum caminhão na fila!");
				}
			}
		});
		btnDescarregarCaminhao.setBounds(822, 77, 137, 23);
		panelMain.add(btnDescarregarCaminhao);

		btnCarregarNavio = new JButton("Carregar");
		btnCarregarNavio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ut.filaNavios.size() > 0) {
					tipo = 1;
					panelCarregar.setVisible(true);
					panelDescarregar.setVisible(false);
					panelCadastro.setVisible(false);
					panelMain.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum navio na fila!");
				}
			}
		});
		btnCarregarNavio.setBounds(291, 43, 137, 23);
		panelMain.add(btnCarregarNavio);

		btnCarregarCaminhao = new JButton("Carregar");
		btnCarregarCaminhao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ut.filaCaminhoes.size() > 0) {
					tipo = 2;
					panelCarregar.setVisible(true);
					panelDescarregar.setVisible(false);
					panelCadastro.setVisible(false);
					panelMain.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Nenhum caminhão na fila!");
				}
			}
		});
		btnCarregarCaminhao.setBounds(822, 43, 137, 23);
		panelMain.add(btnCarregarCaminhao);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 326, 915, 204);
		panelMain.add(scrollPane);

		mContainers = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbContainers = new JTable(mContainers);
		tbContainers.setShowVerticalLines(false);
		tbContainers.setShowHorizontalLines(false);
		scrollPane.setViewportView(tbContainers);
		mContainers.addColumn("Código");
		mContainers.addColumn("Local");
		mContainers.addColumn("Data de Entrada");
		mContainers.addColumn("Endereço Remetente");
		mContainers.addColumn("Remetente");
		mContainers.addColumn("Destino");
		tbContainers.setRowSelectionAllowed(false);

		lblContinersNoPtio = new JLabel("Cont\u00E2iners no p\u00E1tio");
		lblContinersNoPtio.setBounds(44, 299, 241, 14);
		panelMain.add(lblContinersNoPtio);

		JButton btnRemoverDaFilaNavio = new JButton("Liberar");
		btnRemoverDaFilaNavio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				liberarNavio();
			}
		});
		btnRemoverDaFilaNavio.setBounds(291, 111, 137, 23);
		panelMain.add(btnRemoverDaFilaNavio);

		btnRemoverDaFilaCaminhao = new JButton("Liberar");
		btnRemoverDaFilaCaminhao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				liberarCaminhao();
			}
		});
		btnRemoverDaFilaCaminhao.setBounds(822, 111, 137, 23);
		panelMain.add(btnRemoverDaFilaCaminhao);

		lblNavioNaFila = new JLabel("Nenhuma navio na fila!");
		lblNavioNaFila.setBounds(44, 47, 232, 19);
		panelMain.add(lblNavioNaFila);

		lblCaminhaoNaFila = new JLabel("Nenhum Caminhão na fila!");
		lblCaminhaoNaFila.setBounds(575, 47, 241, 19);
		panelMain.add(lblCaminhaoNaFila);

		panelCadastro = new JPanel();
		frmSp.getContentPane().add(panelCadastro, "name_175767671162409");
		panelCadastro.setLayout(null);

		lblNvsCadastrados = new JLabel("Navios Cadastrados");
		lblNvsCadastrados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNvsCadastrados.setBounds(10, 11, 202, 22);
		panelCadastro.add(lblNvsCadastrados);

		listNaviosCadastrados = new List();
		listNaviosCadastrados.setBounds(10, 39, 241, 505);
		panelCadastro.add(listNaviosCadastrados);

		btnRmvNavio = new JButton("Remover Selecionado");

		btnRmvNavio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirNavio();
			}
		});
		btnRmvNavio.setBounds(257, 487, 208, 34);
		panelCadastro.add(btnRmvNavio);

		btnAddNavio = new JButton("Adicionar Navio");
		btnAddNavio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cadastrarNavio();
			}
		});
		btnAddNavio.setBounds(259, 102, 206, 34);
		panelCadastro.add(btnAddNavio);

		tfMatriculaNavio = new JTextField();
		tfMatriculaNavio.setBounds(259, 58, 206, 20);
		panelCadastro.add(tfMatriculaNavio);
		tfMatriculaNavio.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(258, 274, 206, 2);
		panelCadastro.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(258, 432, 206, 2);
		panelCadastro.add(separator_1);

		btnEnviarParaFila = new JButton("Enviar para fila");
		btnEnviarParaFila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarNavioFila();
			}
		});
		btnEnviarParaFila.setBounds(258, 303, 206, 40);
		panelCadastro.add(btnEnviarParaFila);

		lblCmsCadastrados = new JLabel("Caminh\u00F5es Cadastrados");
		lblCmsCadastrados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCmsCadastrados.setBounds(499, 11, 202, 22);
		panelCadastro.add(lblCmsCadastrados);

		btnRemoverCaminhao = new JButton("Remover Selecionado");
		btnRemoverCaminhao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCaminhao();
			}
		});
		btnRemoverCaminhao.setBounds(746, 487, 208, 34);
		panelCadastro.add(btnRemoverCaminhao);

		btnAddCaminhao = new JButton("Adicionar Caminh\u00E3o");
		btnAddCaminhao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarCaminhao();
			}
		});
		btnAddCaminhao.setBounds(746, 102, 206, 34);
		panelCadastro.add(btnAddCaminhao);

		tfPlacaCaminhao = new JTextField();
		tfPlacaCaminhao.setColumns(10);
		tfPlacaCaminhao.setBounds(746, 58, 206, 20);
		panelCadastro.add(tfPlacaCaminhao);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(746, 274, 206, 2);
		panelCadastro.add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(746, 432, 206, 2);
		panelCadastro.add(separator_5);

		btnEnviarCaminhao = new JButton("Enviar para fila");
		btnEnviarCaminhao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarCaminhaoFila();
			}
		});
		btnEnviarCaminhao.setBounds(746, 303, 206, 40);
		panelCadastro.add(btnEnviarCaminhao);

		listCaminhoesCadastrados = new List();
		listCaminhoesCadastrados.setBounds(499, 39, 241, 505);
		panelCadastro.add(listCaminhoesCadastrados);

		panelDescarregar = new JPanel();
		frmSp.getContentPane().add(panelDescarregar, "name_430120870064318");
		panelDescarregar.setLayout(null);

		JLabel lblDescarregando = new JLabel("Descarregando");
		lblDescarregando.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescarregando.setBounds(10, 11, 998, 14);
		panelDescarregar.add(lblDescarregando);

		btnDescarregarContiner = new JButton("Descarregar cont\u00E2iner");
		btnDescarregarContiner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				descarregarContainer();
			}
		});
		btnDescarregarContiner.setBounds(417, 258, 180, 30);
		panelDescarregar.add(btnDescarregarContiner);

		btnTerminarOperaoDe = new JButton("Terminar opera\u00E7\u00E3o de descarregamento");
		btnTerminarOperaoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCarregar.setVisible(false);
				panelDescarregar.setVisible(false);
				panelCadastro.setVisible(false);
				panelMain.setVisible(true);

				ut.salvar();
			}
		});
		btnTerminarOperaoDe.setBounds(721, 527, 287, 48);
		panelDescarregar.add(btnTerminarOperaoDe);

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(432, 36, 150, 14);
		panelDescarregar.add(lblCdigo);

		tfCodigo = new JTextField();
		tfCodigo.setBounds(432, 61, 150, 20);
		panelDescarregar.add(tfCodigo);
		tfCodigo.setColumns(10);

		JLabel lblLocal = new JLabel("Local");
		lblLocal.setBounds(432, 92, 150, 14);
		panelDescarregar.add(lblLocal);

		tfLocal = new JTextField();
		tfLocal.setColumns(10);
		tfLocal.setBounds(432, 117, 150, 20);
		panelDescarregar.add(tfLocal);

		JLabel lblEndRemetente = new JLabel("Endere\u00E7o do Remetente");
		lblEndRemetente.setBounds(432, 148, 150, 14);
		panelDescarregar.add(lblEndRemetente);

		tfEndRemetente = new JTextField();
		tfEndRemetente.setColumns(10);
		tfEndRemetente.setBounds(432, 173, 150, 20);
		panelDescarregar.add(tfEndRemetente);

		tfDestino = new JLabel("Destinat\u00E1rio");
		tfDestino.setBounds(432, 202, 150, 14);
		panelDescarregar.add(tfDestino);

		tfDestinatario = new JTextField();
		tfDestinatario.setColumns(10);
		tfDestinatario.setBounds(432, 227, 150, 20);
		panelDescarregar.add(tfDestinatario);

		panelCarregar = new JPanel();
		frmSp.getContentPane().add(panelCarregar, "name_430142321183662");
		panelCarregar.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 998, 390);
		panelCarregar.add(scrollPane_1);
		tbContainers2 = new JTable(mContainers);
		tbContainers2.setShowVerticalLines(false);
		tbContainers2.setShowHorizontalLines(false);
		tbContainers2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tbContainers2);

		JButton btnCarregarContiner = new JButton("Carregar cont\u00E2iner");
		btnCarregarContiner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				carregarContainer();
			}
		});
		btnCarregarContiner.setBounds(347, 412, 339, 30);
		panelCarregar.add(btnCarregarContiner);

		btnTerminarOperaoDeCarga = new JButton("Terminar opera\u00E7\u00E3o de carga");

		btnTerminarOperaoDeCarga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCarregar.setVisible(false);
				panelDescarregar.setVisible(false);
				panelCadastro.setVisible(false);
				panelMain.setVisible(true);
			}
		});
		btnTerminarOperaoDeCarga.setBounds(721, 541, 287, 48);
		panelCarregar.add(btnTerminarOperaoDeCarga);

		JMenuBar menuBar = new JMenuBar();
		frmSp.setJMenuBar(menuBar);

		JMenuItem mntmIncio = new JMenuItem("In\u00EDcio");
		mntmIncio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCarregar.setVisible(false);
				panelDescarregar.setVisible(false);
				panelCadastro.setVisible(false);
				panelMain.setVisible(true);

			}
		});
		menuBar.add(mntmIncio);

		JMenuItem mntmCadastro = new JMenuItem("Cadastro");
		mntmCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCarregar.setVisible(false);
				panelDescarregar.setVisible(false);
				panelCadastro.setVisible(true);
				panelMain.setVisible(false);
			}
		});
		menuBar.add(mntmCadastro);
	}

	// ----- FUNÇÕES
	public void atualizarArrays() {
		for (int i = 0; i < ut.naviosCadastrados.size(); i++) {
			listNaviosCadastrados.add(ut.naviosCadastrados.get(i).getMatricula());
		}
		for (int i = 0; i < ut.caminhoesCadastrados.size(); i++) {
			listCaminhoesCadastrados.add(ut.caminhoesCadastrados.get(i).getPlaca());
		}
		for (int i = 0; i < ut.containers.size(); i++) {
			Container temp = ut.containers.get(i);
			mContainers.addRow(new Object[] { temp.getCod(), temp.getLoc(), temp.getDataEntrada(),
					temp.getEndRemetente(), temp.getRemetente(), temp.getDestino() });
		}
	}

	public void atualizarListas() {
		if (ut.filaCaminhoes.size() > 0)
			lblCaminhaoNaFila.setText(ut.filaCaminhoes.peek().getPlaca());
		else
			lblCaminhaoNaFila.setText("Nenhum Caminhão na fila!");

		if (ut.filaNavios.size() > 0)
			lblNavioNaFila.setText(ut.filaNavios.peek().getMatricula());
		else
			lblNavioNaFila.setText("Nenhum Navio na fila!");
	}

	// ----- CAMINHÕES
	public void enviarCaminhaoFila() {
		if (listCaminhoesCadastrados.getSelectedIndex() >= 0) {
			int temp = listCaminhoesCadastrados.getSelectedIndex();
			ut.filaCaminhoes.add(ut.caminhoesCadastrados.get(temp));
			atualizarListas();
			ut.salvar();
			JOptionPane.showMessageDialog(null, String.format("Caminhão enviado para fila, posição: %s!", ut.filaCaminhoes.size()));
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum caminhão selecionado!");
		}

	}

	public void cadastrarCaminhao() {
		if (!tfPlacaCaminhao.getText().isEmpty()) {
			Caminhao temp = new Caminhao(tfPlacaCaminhao.getText().toUpperCase());
			Pattern pattern = Pattern.compile("[A-Z]{3}-\\d{4}");
			Matcher matcher = pattern.matcher(tfPlacaCaminhao.getText().toUpperCase());

			if (!matcher.matches()) {
				JOptionPane.showMessageDialog(null, "Insira placa no formato AAA-0000!", "Erro!", 1);
				return;
			}

			for (int i = 0; i < ut.caminhoesCadastrados.size(); i++) {
				if (temp.getPlaca().equals(ut.caminhoesCadastrados.get(i).getPlaca())) {
					JOptionPane.showMessageDialog(null, "Caminhão já cadastrado!", "Erro!", 1);
					return;
				}

			}
			listCaminhoesCadastrados.add(tfPlacaCaminhao.getText().toUpperCase());
			ut.caminhoesCadastrados.add(temp);
			ut.salvar();
		}else {
			JOptionPane.showMessageDialog(null, "Placa de caminhão não pode ser nula!", "Erro!", 1);
			return;
		}
	}

	public void excluirCaminhao() {
		if (listCaminhoesCadastrados.getSelectedIndex() >= 0) {
			int temp = listCaminhoesCadastrados.getSelectedIndex();
			listCaminhoesCadastrados.remove(temp);
			ut.caminhoesCadastrados.remove(temp);
			ut.salvar();
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum caminhão selecionado!");
		}
	}

	public void liberarCaminhao() {
		if (ut.filaCaminhoes.size() > 0) {
			JOptionPane.showMessageDialog(null, String.format("Caminhão %s liberado, ainda restam %s caminhões na fila!", ut.filaCaminhoes.peek().getPlaca(), ut.filaCaminhoes.size()-1));
			ut.filaCaminhoes.poll();
			atualizarListas();
			ut.salvar();
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum camihão na fila!");
		}
	}

	// ----- NAVIOS
	public void enviarNavioFila() {
		if (listNaviosCadastrados.getSelectedIndex() >= 0) {
			int temp = listNaviosCadastrados.getSelectedIndex();
			ut.filaNavios.add(ut.naviosCadastrados.get(temp));
			atualizarListas();
			ut.salvar();
			JOptionPane.showMessageDialog(null, String.format("Navio enviado para fila, posição: %s!", ut.filaNavios.size()));
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum navio selecionado!");
		}
	}

	public void cadastrarNavio() {
		if (!tfMatriculaNavio.getText().isEmpty()) {
			Navio temp = new Navio(tfMatriculaNavio.getText().toUpperCase());
			for (int i = 0; i < ut.naviosCadastrados.size(); i++) {
				if (temp.getMatricula().equals(ut.naviosCadastrados.get(i).getMatricula())) {
					JOptionPane.showMessageDialog(null, "Navio já cadastrado!", "Erro!", 1);
					return;
				}
			}
			if(temp.getMatricula().length() < 5 || temp.getMatricula().length() > 30) {
				JOptionPane.showMessageDialog(null, "Matrícula de navio deve estar estar entre 5 e 30 caracteres!", "Erro!", 1);
				return;
			}
			listNaviosCadastrados.add(tfMatriculaNavio.getText().toUpperCase());
			ut.naviosCadastrados.add(temp);
			ut.salvar();
		}else {
			JOptionPane.showMessageDialog(null, "Matrícula de navio não pode ser nula!", "Erro!", 1);
			return;
		}
	}

	public void excluirNavio() {
		if (listNaviosCadastrados.getSelectedIndex() >= 0) {
			int temp = listNaviosCadastrados.getSelectedIndex();
			listNaviosCadastrados.remove(temp);
			ut.naviosCadastrados.remove(temp);
			ut.salvar();
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum navio selecionado!");
		}
	}

	public void liberarNavio() {
		if (ut.filaNavios.size() > 0) {
			JOptionPane.showMessageDialog(null, String.format("Navio %s liberado, ainda restam %s navios na fila!", ut.filaNavios.peek().getMatricula(), ut.filaNavios.size()-1));
			ut.filaNavios.poll();
			atualizarListas();
			ut.salvar();
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum navio na fila!");
		}
	}

	// ----- CONTAINERS
	// ----- CARREGAR
	public void carregarContainer() {
		if (tbContainers2.getSelectedRow() >= 0) {
			int coluna = 5;
			int linha = tbContainers2.getSelectedRow();
			String value = tbContainers2.getModel().getValueAt(linha, coluna).toString();
			if (tipo == 1) {
				if (value.equals(ut.filaNavios.peek().getMatricula())) {
					JOptionPane.showMessageDialog(null, String.format("Contâiner %s, carregado com sucesso",
							tbContainers2.getModel().getValueAt(linha, 0).toString()));
					mContainers.removeRow(linha);
					ut.containers.remove(linha);
					ut.salvar();
				} else {
					JOptionPane.showMessageDialog(null, "Container selecionado não pertence a navio na fila!");
				}
			} else if (tipo == 2) {
				if (value.equals(ut.filaCaminhoes.peek().getPlaca())) {
					JOptionPane.showMessageDialog(null, String.format("Contâiner %s, carregado com sucesso",
							tbContainers2.getModel().getValueAt(linha, 0).toString()));
					mContainers.removeRow(linha);
					ut.containers.remove(linha);
					ut.salvar();
				} else {
					JOptionPane.showMessageDialog(null, "Container selecionado não pertence a caminhão na fila!");
				}
			}
		}
	}

	// ----- DESCARREGAR
	public void descarregarContainer() {
		String rem = null;
		if (tipo == 1) {
			rem = ut.filaNavios.peek().getMatricula();
		} else if (tipo == 2) {
			rem = ut.filaCaminhoes.peek().getPlaca();
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String data = dateFormat.format(date);

		String local = tfLocal.getText().toUpperCase();
		Pattern pattern = Pattern.compile("[A-Z]{1}\\d{2}");
		Matcher matcher = pattern.matcher(local);

		String cod = tfCodigo.getText().toUpperCase();
		Pattern pattern2 = Pattern.compile("[A-Z]{3}U\\d{7}");
		Matcher matcher2 = pattern2.matcher(cod);

		String dest = tfDestinatario.getText().toUpperCase();

		String endRem = tfEndRemetente.getText().toUpperCase();

		for (int i = 0; i < ut.containers.size(); i++) {
			if (ut.containers.get(i).getCod().equals(cod)) {
				JOptionPane.showMessageDialog(null, "Contâiner já está no pátio!", "Erro!", 1);
				return;
			}
		}
		if (!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "Local deve ser uma sequência de uma letra e dois números (e.g A00)!",
					"Erro!", 1);
			return;
		}
		if (endRem.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Endereço do remetente não pode ser vazio!", "Erro!", 1);
			return;
		}
		if (dest.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Destinatário não pode ser vazio!", "Erro!", 1);
			return;
		}
		if (!matcher2.matches()) {
			JOptionPane.showMessageDialog(null, "Código deve ser formado por 4 letras (sendo a última U) e 7 números!",
					"Erro!", 1);
			return;
		}

		Container temp = new Container(cod, local, data, endRem, rem, dest);
		ut.containers.add(temp);
		ut.salvar();
		mContainers.addRow(new Object[] { temp.getCod(), temp.getLoc(), temp.getDataEntrada(), temp.getEndRemetente(),
				temp.getRemetente(), temp.getDestino() });
		JOptionPane.showMessageDialog(null, String.format("Container %s descarregado!", cod), "Sucesso!", 1);
	}

}
