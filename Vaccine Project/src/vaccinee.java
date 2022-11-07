import java.awt.*;
import javax.swing.*;
import java.util.Queue;
import java.util.Stack;
import java.awt.event.*;
import java.util.LinkedList;

public class vaccinee extends JFrame implements ActionListener{

	private Container cont;
	private JList<String> list18_30, list31_49, list50above;
	public String[] List1, List2, List3;
	private JButton btnContinue, btnBack;
	private Queue<citizen> qCenter1,qCenter2, qCenter3, qTemp1, qTemp2, qTemp3;
	private Stack<citizen> stCenter1, stCenter2, stCenter3, stTemp1, stTemp2, stTemp3;
	private String option;
	private LinkedList<citizen> completedList;

	/**
	 * Create the frame.
	 */
	public vaccinee(Stack<citizen> stCenter1, Stack<citizen> stCenter2, Stack<citizen> stCenter3, Queue<citizen> qCenter1, Queue<citizen> qCenter2, Queue<citizen> qCenter3, LinkedList<citizen> completedList, String optional) {
		String title = optional.equalsIgnoreCase("1stdose") ? "Dose 1" : "Dose 2";

		this.setTitle(title);
		this.option = optional;
		this.completedList = completedList;

		this.stCenter1 = stCenter1;
		this.stCenter2 = stCenter2;
		this.stCenter3 = stCenter3;

		this.qCenter1 = qCenter1;
		this.qCenter2 = qCenter2;
		this.qCenter3 = qCenter3;

		this.stTemp1 = new Stack<>();
		this.stTemp2 = new Stack<>();
		this.stTemp3 = new Stack<>();

		this.qTemp1 = new LinkedList<>();
		this.qTemp2 = new LinkedList<>();
		this.qTemp3 = new LinkedList<>();

		cont = getContentPane();
		cont.setLayout(new GridLayout(5, 3, 2, 2));
        
		JLabel lbl18_30 = new JLabel("18-30");
		lbl18_30.setHorizontalAlignment(SwingConstants.CENTER);
		cont.add(lbl18_30);

		int size1 = optional.equalsIgnoreCase("1stdose") ? stCenter1.size() : qCenter1.size();
		List1 = new String [size1];
		for (int i = 0; i < List1.length; i++) {
			citizen person = optional.equalsIgnoreCase("1stdose") ? stCenter1.pop() : qCenter1.remove();
			Boolean _b = optional.equalsIgnoreCase("1stdose") ? stTemp1.add(person) : qTemp1.add(person);
			List1[i] = person.getName();
		}
		
        list18_30 = new JList(List1);
        list18_30.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
				list31_49.clearSelection();
				list50above.clearSelection();
        	}
        });
		list18_30.setVisibleRowCount(5);
		cont.add(new JScrollPane(list18_30));
		
		JLabel lbl31_49 = new JLabel("31-49");
		lbl31_49.setHorizontalAlignment(SwingConstants.CENTER);
		cont.add(lbl31_49);
		
		int size2 = optional.equalsIgnoreCase("1stdose") ? stCenter2.size() : qCenter2.size();
		List2 = new String [size2];
		for (int i = 0; i < List2.length; i++) {
			citizen person = optional.equalsIgnoreCase("1stdose") ? stCenter2.pop() : qCenter2.remove();
			Boolean _b = optional.equalsIgnoreCase("1stdose") ? stTemp2.add(person) : qTemp2.add(person);
			List2[i] = person.getName();
		}
		
		list31_49 = new JList(List2);
		list31_49.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(seCenter2);
				// if(list31_49.getSelectedValue().equalsIgnoreCase(seCenter2)){
				// 	list31_49.clearSelection();
				// }
				list18_30.clearSelection();
				list50above.clearSelection();
				// seCenter2 = list31_49.getSelectedValue();
        	}
		});
		list31_49.setVisibleRowCount(5);
		cont.add(new JScrollPane(list31_49));
		
		JLabel lbl50above = new JLabel("50 and above");
		lbl50above.setHorizontalAlignment(SwingConstants.CENTER);
		cont.add(lbl50above);
		
		int size3 = optional.equalsIgnoreCase("1stdose") ? stCenter3.size() : qCenter3.size();
		List3 = new String [size3];
		for (int i = 0; i < List3.length; i++) {
			citizen person = optional.equalsIgnoreCase("1stdose") ? stCenter3.pop() : qCenter3.remove();
			Boolean _b = optional.equalsIgnoreCase("1stdose") ? stTemp3.add(person) : qTemp3.add(person);
			
			List3[i] = person.getName();
		}

		list50above = new JList(List3);
		list50above.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(seCenter3);
				// if(list50above.getSelectedValue().equalsIgnoreCase(seCenter3)){
				// 	list50above.clearSelection();
				// }
				list18_30.clearSelection();
				list31_49.clearSelection();
				// seCenter3 = list50above.getSelectedValue();
        	}
		});
		list50above.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list50above.setVisibleRowCount(5);
		cont.add(new JScrollPane(list50above));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setBounds(47, 33, 101, 21);
		panel.add(btnBack);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		cont.add(panel_1);
		
		btnContinue = new JButton("Continue");
		btnContinue.setBounds(35, 33, 101, 21);
		panel_1.add(btnContinue);
		btnContinue.addActionListener(this);

		setBounds(200, 200, 450, 300);
        setSize(363,435);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
        setResizable(false);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnContinue){
			JList[] lJLists = {list18_30, list31_49, list50above};
			for (JList<String> jList : lJLists) {
				String name = jList.getSelectedValue();
				if(name != null)
					sendBack(name);
				jList.clearSelection();
			}
			// System.out.println(seCenter1+' '+seCenter2+' '+seCenter3);
		}

		if(e.getSource() == btnBack){
			sendBack("");
		}
	}

	public void sendBack(String name){
		int size1 = option.equalsIgnoreCase("1stdose") ? stTemp1.size() : qTemp1.size();
		for (int i = 0; i < size1; i++) {
			citizen person = option.equalsIgnoreCase("1stdose") ? stTemp1.pop() : qTemp1.remove();
			if(option.equalsIgnoreCase("1stdose")){
				if(person.getName().equalsIgnoreCase(name)){
					person.setStat1stDose("Done");
					qCenter1.add(person);
					continue;
				}
				stCenter1.add(person);
			}
			else{
				if(person.getName().equalsIgnoreCase(name)){
					person.setStat2ndDose("Done");
					person.setCertificate("Complete");
					completedList.add(person);
					continue;
				}
				qCenter1.add(person);
			}
		}
		
		int size2 = option.equalsIgnoreCase("1stdose") ? stTemp2.size() : qTemp2.size();
		for (int i = 0; i < size2; i++) {
			citizen person = option.equalsIgnoreCase("1stdose") ? stTemp2.pop() : qTemp2.remove();
			if(option.equalsIgnoreCase("1stdose")){
				if(person.getName().equalsIgnoreCase(name)){
					person.setStat1stDose("Done");
					qCenter2.add(person);
					continue;
				}
				stCenter2.add(person);
			}
			else{
				if(person.getName().equalsIgnoreCase(name)){
					person.setStat2ndDose("Done");
					person.setCertificate("Complete");
					completedList.add(person);
					continue;
				}
				qCenter2.add(person);
			}
		}

		int size3 = option.equalsIgnoreCase("1stdose") ? stTemp3.size() : qTemp3.size();
		for (int i = 0; i < size3; i++) {
			citizen person = option.equalsIgnoreCase("1stdose") ? stTemp3.pop() : qTemp3.remove();
			// if(!person.getName().equalsIgnoreCase(name)){
			if(option.equalsIgnoreCase("1stdose")){
				if(person.getName().equalsIgnoreCase(name)){
						person.setStat1stDose("Done");
						qCenter3.add(person);
					continue;
				}
				stCenter3.add(person);
			}
			else{
				if(person.getName().equalsIgnoreCase(name)){
					person.setStat2ndDose("Done");
					person.setCertificate("Complete");
					completedList.add(person);
					continue;
				}
				qCenter3.add(person);
			}
		}
		vaccination frame = new vaccination(stCenter1, stCenter2, stCenter3,qCenter1, qCenter2, qCenter3, completedList);
		frame.setVisible(true);
		dispose();
	}
}