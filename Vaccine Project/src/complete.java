import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
import java.awt.event.*;
import java.util.Queue;
import java.util.Stack;

public class complete extends JFrame implements ActionListener{

	private Container cont;
	private JList<String> list;
	private Queue<citizen> qCenter1,qCenter2, qCenter3;
	private Stack<citizen> stCenter1, stCenter2, stCenter3;
	private LinkedList<citizen> completedList;
	private JButton btnBack;

	/**
	 * Create the frame.
	 */
	public complete(Stack<citizen> stCenter1, Stack<citizen> stCenter2, Stack<citizen> stCenter3, Queue<citizen> qCenter1, Queue<citizen> qCenter2, Queue<citizen> qCenter3, LinkedList<citizen> completedList) {
		
		cont = getContentPane();
		cont.setLayout(null);

		this.completedList = completedList;

		this.stCenter1 = stCenter1;
		this.stCenter2 = stCenter2;
		this.stCenter3 = stCenter3;

		this.qCenter1 = qCenter1;
		this.qCenter2 = qCenter2;
		this.qCenter3 = qCenter3;

		String[] List= new String[completedList.size()];
		for (int i = 0; i < List.length; i++) {
			List[i] = completedList.get(i).getName();
		}
		
		list = new JList(List);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(5);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < completedList.size(); i++) {
					if(completedList.get(i).getName().equalsIgnoreCase(list.getSelectedValue()))
						JOptionPane.showMessageDialog(null, completedList.get(i), "Certificate", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		list.setBounds(26, 41, 132, 58);
		// cont.add(new JScrollPane(list));
		
		JLabel lblNewLabel = new JLabel("Complete vaccination");
		lblNewLabel.setBounds(28, 18, 148, 13);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		cont.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(24, 39, 152, 96);
		panel.add(new JScrollPane(list));
		cont.add(panel);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(24, 145, 85, 21);
		btnBack.addActionListener(this);
		cont.add(btnBack);
		
		setBounds(100, 100, 238, 246);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnBack){
			vaccination frame = new vaccination(stCenter1, stCenter2, stCenter3,qCenter1, qCenter2, qCenter3, completedList);
			frame.setVisible(true);
			dispose();
		}
	}
}