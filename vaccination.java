import java.awt.*;
import javax.swing.*;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.event.*;

public class vaccination extends JFrame implements ActionListener{

	private Container cont;
	private JButton btn1stdose, btn2nddose, btnComplete, btnBack;
	private Queue<citizen> qCenter1,qCenter2, qCenter3;
	private Stack<citizen> stCenter1, stCenter2, stCenter3;
	private LinkedList<citizen> completedList;

	/**
	 * Create the frame.
	 */
	public vaccination(Stack<citizen> stCenter1, Stack<citizen> stCenter2, Stack<citizen> stCenter3, Queue<citizen> qCenter1, Queue<citizen> qCenter2, Queue<citizen> qCenter3, LinkedList<citizen> completedList) {
		super("Vaccination");

		// this.setStack(CitizenList);
		this.completedList = completedList;

		this.stCenter1 = stCenter1;
		this.stCenter2 = stCenter2;
		this.stCenter3 = stCenter3;

		this.qCenter1 = qCenter1;
		this.qCenter2 = qCenter2;
		this.qCenter3 = qCenter3;

		cont = getContentPane();
        cont.setLayout(null);
        
        btn1stdose = new JButton("1st Dose");
        btn1stdose.setBounds(32, 88, 85, 21);
		btn1stdose.addActionListener(this);
		cont.add(btn1stdose);
        
        btn2nddose = new JButton("2nd Dose");
        btn2nddose.setBounds(139, 88, 93, 21);
		btn2nddose.addActionListener(this);
		cont.add(btn2nddose);
        
        btnComplete = new JButton("Completed");
        btnComplete.setBounds(251, 88, 100, 21);
		btnComplete.addActionListener(this);
		cont.add(btnComplete);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(32, 136, 85, 21);
		btnBack.addActionListener(this);
		cont.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Continue with  :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(32, 46, 319, 13);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		cont.add(lblNewLabel);

		


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 222);
		setLocationRelativeTo(null);
	}

	// 
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btn1stdose){
			// int size = stCenter1.size();
			// LinkedList<String> name = new LinkedList<>();
			// for (int i = 0; i < size; i++) {
			// 	name.add(stCenter1.pop().getName());
			// }
			System.out.println(stCenter1.size()+" "+stCenter2.size()+' '+stCenter3.size()+' '+qCenter1.size()+' '+qCenter2.size()+' '+qCenter3.size()+' '+completedList.size());

			vaccinee frame = new vaccinee(stCenter1, stCenter2, stCenter3,qCenter1, qCenter2, qCenter3, completedList, "1stdose");
			frame.setVisible(true);
			dispose();
		}
		else if(e.getSource() == btn2nddose){
			System.out.println(stCenter1.size()+" "+stCenter2.size()+' '+stCenter3.size()+' '+qCenter1.size()+' '+qCenter2.size()+' '+qCenter3.size()+' '+completedList.size());
			vaccinee frame = new vaccinee(stCenter1, stCenter2, stCenter3,qCenter1, qCenter2, qCenter3, completedList, "2nddose");
			frame.setVisible(true);
			dispose();
		}

		else if(e.getSource() == btnComplete){
			System.out.println(completedList.size());
			complete frame = new complete(stCenter1, stCenter2, stCenter3,qCenter1, qCenter2, qCenter3, completedList);
			frame.setVisible(true);
			dispose();
		}

		else if(e.getSource() == btnBack){
			LinkedList<citizen> CitizenList = new LinkedList<>();
			while(!stCenter1.isEmpty()){
				CitizenList.add(stCenter1.pop());
			}while(!stCenter2.isEmpty()){
				CitizenList.add(stCenter2.pop());
			}while(!stCenter3.isEmpty()){
				CitizenList.add(stCenter3.pop());
			}while(!qCenter1.isEmpty()){
				CitizenList.add(qCenter1.remove());
			}while(!qCenter2.isEmpty()){
				CitizenList.add(qCenter2.remove());
			}while(!qCenter3.isEmpty()){
				CitizenList.add(qCenter3.remove());
			}while(!completedList.isEmpty()){
				CitizenList.add(completedList.pop());
			}
			
			Test frame = new Test(CitizenList);
			frame.setVisible(true);
			dispose();
		}
	}
}
