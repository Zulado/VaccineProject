import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;



public class form extends JFrame implements ActionListener{

	private Container cont;
	private JTextField txtName, txtAge, txtState, txtIc, txt1stdose, txt2nddose, txtCertificate, txtCategory;
	private JButton btnSubmit, btnBack;
	private String option;
    private LinkedList<citizen> CitizenList; 
    private citizen person;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// 	EventQueue.invokeLater(new Runnable() {
	// 		public void run() {
	// 			try {
	// 				Form frame = new Form();
	// 				frame.setVisible(true);
	// 			} catch (Exception e) {
	// 				e.printStackTrace();
	// 			}
	// 		}
	// 	});
	// }

	/**
	 * Create the frame.
	 */
	public form(String option, LinkedList<citizen> citizen, citizen person) {
		super("Input Form");

		this.option = option;
        this.CitizenList = citizen;
        this.person = person;

		cont = getContentPane();
        cont.setLayout(null);
        
        JLabel lblName = new JLabel("Name :");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblName.setBounds(32, 32, 85, 13);
        cont.add(lblName);
        
        JLabel lblState = new JLabel("State :");
        lblState.setBounds(32, 74, 85, 13);
		lblState.setFont(new Font("Tahoma", Font.BOLD, 13));
        cont.add(lblState);
        
        JLabel lblIc = new JLabel("IC :");
        lblIc.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblIc.setBounds(32, 53, 85, 13);
        cont.add(lblIc);
        
        JLabel lblAge = new JLabel("Age :");
        lblAge.setBounds(32, 95, 85, 13);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 13));
        cont.add(lblAge);
        
        JLabel lblCategory = new JLabel("Category :");
        lblCategory.setBounds(32, 118, 85, 13);
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 13));
        cont.add(lblCategory);
        
        JLabel lbl1stdose = new JLabel("1st dose status :");
        lbl1stdose.setBounds(32, 141, 119, 13);
		lbl1stdose.setFont(new Font("Tahoma", Font.BOLD, 13));
        cont.add(lbl1stdose);
        
        JLabel lbl2nddose = new JLabel("2nd dose status :");
        lbl2nddose.setBounds(32, 164, 119, 13);
		lbl2nddose.setFont(new Font("Tahoma", Font.BOLD, 13));
        cont.add(lbl2nddose);
        
        JLabel lblCertificate = new JLabel("Certificate :");
        lblCertificate.setBounds(32, 187, 85, 13);
		lblCertificate.setFont(new Font("Tahoma", Font.BOLD, 13));
        cont.add(lblCertificate);
        
        txtName = new JTextField();
        txtName.setBounds(150, 32, 151, 19);
        cont.add(txtName);
        txtName.setColumns(10);
        
        txtIc = new JTextField();
        txtIc.setColumns(10);
        txtIc.setBounds(150, 53, 151, 19);
        cont.add(txtIc);
        
        txtState = new JTextField();
        txtState.setColumns(10);
        txtState.setBounds(150, 74, 151, 19);
        cont.add(txtState);
        
        txtAge = new JTextField();
        txtAge.setColumns(10);
        txtAge.setBounds(150, 95, 151, 19);
        cont.add(txtAge);
        
        txtCategory = new JTextField();
        txtCategory.setColumns(10);
        txtCategory.setBounds(150, 116, 151, 19);
        cont.add(txtCategory);
        
        txt1stdose = new JTextField();
        txt1stdose.setColumns(10);
        txt1stdose.setBounds(150, 137, 151, 19);
        cont.add(txt1stdose);
        
        txt2nddose = new JTextField();
        txt2nddose.setColumns(10);
        txt2nddose.setBounds(150, 158, 151, 19);
        cont.add(txt2nddose);
        
        txtCertificate = new JTextField();
        txtCertificate.setColumns(10);
        txtCertificate.setBounds(150, 179, 151, 19);
        cont.add(txtCertificate);
        
        btnSubmit = new JButton("SUBMIT");
        btnSubmit.setBounds(171, 231, 85, 21);
		btnSubmit.addActionListener(this);
        cont.add(btnSubmit);
        
        btnBack = new JButton("BACK");
        btnBack.setBounds(74, 231, 85, 21);
        btnBack.addActionListener(this);
        cont.add(btnBack);

        if(option.equalsIgnoreCase("UPDATE")){ 
            txtName.setText(person.getName());
            txtIc.setText(person.getIc());
            txtIc.setEditable(false);
            txtState.setText(person.getState());
            txtAge.setText(person.getAge()+"");
            txt1stdose.setText(person.getStat1stDose());
            txt2nddose.setText(person.getStat2ndDose());
            txtCertificate.setText(person.getCertificate());
            txtCategory.setText(person.getCategory());
        }
        
		setBounds(200, 200, 450, 300);
        setSize(363,327);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnSubmit){

            JTextField field[] = {txtName, txtAge, txtState, txtIc, txtCategory};
            boolean empty = false;
            for (int i = 0; i < field.length; i++) {
                if(field[i].getText().equalsIgnoreCase("")){
                    empty = true;
                    break;
                }
            }
            if(empty){
                JOptionPane.showMessageDialog(null, "Please dont leave input field empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

			if(option.equalsIgnoreCase("ADD")){
                String dose1 = txt1stdose.getText().equalsIgnoreCase("") ? null : txt1stdose.getText();
                String dose2 = txt2nddose.getText().equalsIgnoreCase("") ? null : txt2nddose.getText();
                String Certificicate = txtCertificate.getText().equalsIgnoreCase("") ? null : txtCertificate.getText();

                citizen citizen = new citizen(txtName.getText(), txtIc.getText(), txtState.getText(), Integer.parseInt(txtAge.getText()), txtCategory.getText(), dose1, dose2, Certificicate);
                
                CitizenList.add(citizen);

				Test frame = new Test(CitizenList);
				frame.setVisible(true);
				dispose();
			}
            else if(option.equalsIgnoreCase("UPDATE")){
                for (int i = 0; i < CitizenList.size(); i++) {
                    if(CitizenList.get(i).getIc() == person.getIc()){
                        CitizenList.set(i, person);
                    }
                }
                
				Test frame = new Test(CitizenList);
				frame.setVisible(true);
				dispose();
			}
		}
        else if(e.getSource() == btnBack){
            Test frame = new Test(CitizenList);
            frame.setVisible(true);
            dispose();
        }
	}
}