package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.AnalysisTable;
import main.Dictionary;
import main.DictionaryEntry;

public class MainFrame extends JFrame{

	private JEditorPane resultPane;
	private JEditorPane analysisPane;
	
	public MainFrame(){
		setSize(800, 400);
		setVisible(true);
		setTitle("Dictionary Search");
		getContentPane().setLayout(new BorderLayout(5,5));
		
		JPanel searchPanel = createSearchPanel();
		getContentPane().add(searchPanel, BorderLayout.PAGE_START);
		
		resultPane = createResultPane();
		getContentPane().add(resultPane, BorderLayout.CENTER);
		
		analysisPane = createAnalysisPane();
		getContentPane().add(analysisPane, BorderLayout.PAGE_END);

	}

	public JEditorPane getResultPane() {
		return resultPane;
	}

	public JEditorPane getAnalysisPane() {
		return analysisPane;
	}

	private JPanel createSearchPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setBackground(Color.WHITE);
		JLabel label = new JLabel("Word to Search For: ");
		panel.add(label);
		JTextField searchTextField = new JTextField(20);
		panel.add(searchTextField);
		JButton searchButton = new JButton("Search WordNet");
		panel.add(searchButton);	
		
		searchButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String searchFor = searchTextField.getText();
		    	AnalysisTable aTable = new AnalysisTable();
		    	aTable.setKey(searchFor.replaceAll(" ", "_").toLowerCase());
		    	Dictionary.searchAndAnalyze(aTable);
		    	DictionaryEntry result = aTable.getEntry();
		    	//DictionaryEntry result = Dictionary.search(searchFor.replaceAll(" ", "_").toLowerCase());
		    	if(result == null){
		    		resultPane.setText("<h1>Word NOT FOUND</h1>");
		    	}else{
		    		resultPane.setText(result.toHTMLString());
		    	}

	    		analysisPane.setText(aTable.toHTMLString());
		    }
		});
		
		searchTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchButton.doClick();
			}
			
			
		});
		
		return panel;
	}
	
	private JEditorPane createResultPane(){
		JEditorPane pane = new JEditorPane();
	    pane.setContentType("text/html");
	    pane.setEditable(false);
        return pane;
	}
	
	private JEditorPane createAnalysisPane(){
		JEditorPane pane = new JEditorPane();
	    pane.setContentType("text/html");
	    pane.setEditable(false);
        return pane;
	}
	
	/*private void addResultPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 20));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		JLabel label = new JLabel("NOUN");		
		label.setFont(new Font(label.getFont().getName(), Font.BOLD, 20));
		panel.add(label);
		
		JPanel defPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
		defPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel word = new JLabel();	
		word.setText(". data structure");
		JLabel definition = new JLabel("	");		
		label.setFont(new Font(label.getFont().getName(), Font.BOLD, 20));
		panel.add(label);		
	}*/

}
