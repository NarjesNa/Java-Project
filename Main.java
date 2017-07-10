package exercice_reseau_bio;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.graphstream.graph.Graph;
import org.graphstream.ui.swingViewer.*;
import org.graphstream.ui.view.Viewer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 * 
 * @author Narjes NALOUTI
 *
 */

public class Main extends JFrame implements ActionListener, ListSelectionListener{

	private static final long serialVersionUID = 1L;

	private JLayeredPane lpane = new JLayeredPane();
	protected JButton bouton2;
	protected JMenuBar bar = new JMenuBar();
	protected JMenu menuCartes = new JMenu("Cards");
	protected JMenu menuCapture = new JMenu("Capture");
	protected JMenu menuHelp = new JMenu("Help");
	protected JMenuItem itemGenerique = new JMenu("Generic");
	protected JMenuItem itemSpecifique = new JMenu("Specific");
	protected JMenuItem itemCapture = new JMenu("Capture");
	protected JMenuItem itemHelp = new JMenu("Help");

	// BARRE DE RECHERCHE
	protected JPanel directory = new JPanel();
	protected final JLabel directoryLabel = new JLabel("Directory");
	protected JTextField textField = new JTextField(30);// champs de texte
	protected JButton Browse = new JButton ("..."); //bouton parcourir

	//      private JComboBox pathList;
	//      private JComboBox speciesList;

	protected JScrollPane jspPath;
	protected JScrollPane jspOrg;
	protected JList<String> pathMenu; //menu deroulant contenant les path
	protected JList<String> speciesMenu; //menu deroulant contenant les org

	protected DefaultListModel<String> pathMenuList = new DefaultListModel<String>();
	protected DefaultListModel<String> espMenuList = new DefaultListModel<String>();

	// MENUS DEROULANTS
	protected JPanel panelMenuDeroulant = new JPanel(new GridBagLayout());
	protected final JLabel pathMenuLabel= new JLabel("Pathway");
	protected final JLabel speciesMenuLabel= new JLabel("Species");

	// GRAPHIQUE
	protected JPanel panelGraphe = new JPanel(new GridBagLayout());
	protected JScrollPane jspPanelGraph;
	protected JLabel NomDuPathway;

	protected Graphe graphic;
	//        PROTECTED GRAPHE GRAPH;
	protected Viewer viewer;

	public Main(){
		super();
		build();//On initialise notre fenêtre
	}

	private void build(){
		setTitle("Metabolic Pathways");
		setSize(600,400);
		setLocationRelativeTo(null); // fenetre centrée sur l'écran
		setResizable(true); // redimensionnement possible de la fenetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // se ferme lors du clic sur la croix
		buildContentPane();
	}

	private void buildContentPane(){

		itemGenerique.addMouseListener(new SimpleListener());
		itemSpecifique.addMouseListener(new SimpleListener());
		itemCapture.addMouseListener(new SimpleListener());
		itemHelp.addMouseListener(new SimpleListener());
		menuCartes.add(itemGenerique);
		menuCartes.add(itemSpecifique);
		menuCapture.add(itemCapture);
		menuHelp.add(itemHelp);
		bar.add(menuCartes);
		bar.add(menuCapture);
		bar.add(menuHelp);
		setJMenuBar(bar);


		// Barre de recherche du chemin
		directory.add(directoryLabel);
		directory.add(textField);
		directory.add(Browse);
		add(directory,BorderLayout.NORTH);


		// PATHMENU ET SPECIES MENU SE CHARGENT
		this.pathMenu = new JList<String>(pathMenuList);
		this.speciesMenu = new JList<String>(espMenuList);
		pathMenu.setFixedCellHeight(20);
		pathMenu.setFixedCellWidth(100);
		pathMenu = new JList<String>(getPathList());
		speciesMenu = new JList<String>(getSpeciesList());
		speciesMenu.setFixedCellHeight(20);
		speciesMenu.setFixedCellWidth(100);
		jspPath = new JScrollPane(pathMenu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		jspOrg = new JScrollPane(speciesMenu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pathMenu.setVisibleRowCount(4);
		speciesMenu.setVisibleRowCount(4);
		//              pathMenu.setVisible(false);
		//              speciesMenu.setVisible(false);

		// ON GERE LES CONTRAINTES SUR LES MENUS
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.insets = new Insets(5,5,5,5);
		contrainte.gridx = 0; contrainte.gridy = 1;
		panelMenuDeroulant.add(pathMenuLabel,contrainte);
		contrainte.gridy = 2;
		panelMenuDeroulant.add(jspPath,contrainte);
		contrainte.gridy = 3;
		panelMenuDeroulant.add(speciesMenuLabel,contrainte);
		contrainte.gridy = 4;
		panelMenuDeroulant.add(jspOrg,contrainte);

		add(panelMenuDeroulant,BorderLayout.EAST);

		// ON GERE LE PANEL DU GRAPHIQUE
		panelGraphe.setBackground(Color.white);
		panelGraphe.setPreferredSize(new Dimension(200,200));
		jspPanelGraph= new JScrollPane(panelGraphe, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		add(jspPanelGraph,FlowLayout.RIGHT);

		Browse.addActionListener(this);
		pathMenu.addListSelectionListener(this);
		speciesMenu.addListSelectionListener(this);

	}
	/**
	 * Recupere la liste des especes (code)
	 * @return array (liste)
	 */

	public String[] getSpeciesList() {
		File repertoire = new File("/Users/Narjes/Desktop/EDC/Bacteria");
		File[] dossiers = repertoire.listFiles();
		List<String> listEspeces = new ArrayList<String>();
		for (File d : dossiers)
			if (!d.getName().contains(".DS"))
				listEspeces.add(d.getName().substring(0, 3));

		String[] array = listEspeces.toArray(new String[0]);
		return array;

	}
	
	/**
	 * Recupere la liste des codes des voies 
	 * @return array (liste) 
	 */

	public String[] getPathList() {
		File repertoire = new File("/Users/Narjes/Desktop/EDC/Bacteria");
		File[] dossiers = repertoire.listFiles();
		HashSet <String> hashPaths = new HashSet<String>();
		List <String> listPaths = new ArrayList<>();
		File[] fichiers = null;
		for (File d : dossiers)
			if (d.isDirectory())
				fichiers = d.listFiles();
		for (File f : fichiers)
			hashPaths.add(f.getName().substring(0, 4)+f.getName().substring(9, 13));

		listPaths.clear();
		listPaths.addAll(hashPaths);
		Collections.sort(listPaths, new Comparator<String>() {
			@Override
			public int compare(String path1, String path2) {
				return  path1.compareTo(path2);
			}
		});

		String[] array = listPaths.toArray(new String[0]);
		return array;
	}
	

	public JList<String> getListe1(){
		return pathMenu;
	}

	public JList<String> getListe2(){
		return speciesMenu;
	}

// action listener sur la barre de recherche// 
	public void actionPerformed(ActionEvent e) {

		JFileChooser Directory = new JFileChooser();
		Directory.setCurrentDirectory(new File(System.getProperty("user.home")));

		Directory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int result = Directory.showSaveDialog(null);
		//if the user click on save in Jfilechooser
		if(result == JFileChooser.APPROVE_OPTION)
		{
			File selectedDirectory = Directory.getSelectedFile();
			String path = selectedDirectory.getAbsolutePath();
			textField.setText(path);

		}

	}
	
	/**
	 * Méthode main qui rend la fenetre visible
	 * @param args
	 */
	public static void main(String[] args) {
		// Creation d'une nouvelle instance de la fenetre
		Main fenetre = new Main();
		fenetre.setVisible(true);//On la rend visible
	}
 // listener sur les menus : path et species 
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == pathMenu && !e.getValueIsAdjusting())
		{
			String selectedValue = (String) this.getListe1().getSelectedValue().toString().substring(4);

			graphic = new Graphe(selectedValue);
			viewer = new Viewer(graphic.getGraphe(), Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
			viewer.enableAutoLayout();

			ViewPanel view = viewer.addDefaultView(false);

			// Add the view to self and make it visible
			setVisible(true);
			view.setSize(new Dimension(200, 200));
			panelGraphe.setLayout(new BorderLayout());
			panelGraphe.add(view, BorderLayout.CENTER);

			// Refresh the window
			repaint();
			revalidate();

		}
		if (e.getSource() == speciesMenu && !e.getValueIsAdjusting())
		{
			String selectedValue = (String) this.getListe2().getSelectedValue().toString();
			System.out.println("org");
			//graphic.colorsheet(selectedValue);
		}
	}
}