import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;
import java.util.StringTokenizer;

public class Test extends JFrame implements ActionListener{

	private Container cont;
	private JButton btnAdd, btnRemove, btnUpdate, btnContinue;
	private JLabel lblLoader;
	private LinkedList<citizen> CitizenList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException {
		LinkedList<citizen> citizenlist = new LinkedList<>();
		try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\eclipse-workspace\\Vaccine Project\\src\\citizen.txt"));
            citizen citizen;

            String line = br.readLine();
            while (line != null) {
                StringTokenizer st = new StringTokenizer(line, ";");
                String name = st.hasMoreTokens() ? st.nextToken() : null;
                String ic = st.hasMoreTokens() ? st.nextToken() : null;
                String state = st.hasMoreTokens() ? st.nextToken() : null;
                int age = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;
                String category = st.hasMoreTokens() ? st.nextToken() : null;
                String stat1stDose = st.hasMoreTokens() ? st.nextToken() : null;
                String stat2ndDose = st.hasMoreTokens() ? st.nextToken() : null;
                String certificate = st.hasMoreTokens() ? st.nextToken() : null;
				
                citizen = new citizen(name, ic, state, age, category, stat1stDose, stat2ndDose, certificate);
                citizenlist.add(citizen);

                line = br.readLine();
            }

            br.close();
        } catch (EOFException ex) {
			System.out.println("End of file error");
		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
		} catch (IOException ex) {
			System.out.println("Wrong input!!!");
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		Test frame = new Test(citizenlist);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Test(LinkedList<citizen> CitizenList) {
		super("Citizen");

		this.CitizenList = CitizenList;

		cont = getContentPane();
        cont.setLayout(null);
        
        lblLoader = new JLabel(CitizenList.size()+" Citizen have loaded");
        lblLoader.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoader.setBounds(10, 23, 295, 13);
        cont.add(lblLoader);
        
        btnAdd = new JButton("Add");
        btnAdd.setBounds(10, 55, 88, 21);
		btnAdd.addActionListener(this);
        cont.add(btnAdd);
        
        btnRemove = new JButton("Remove");
        btnRemove.setBounds(115, 55, 88, 21);
		btnRemove.addActionListener(this);
        cont.add(btnRemove);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(217, 55, 88, 21);
		btnUpdate.addActionListener(this);
        cont.add(btnUpdate);
        
        btnContinue = new JButton("Continue");
        btnContinue.setBounds(217, 96, 88, 21);
		btnContinue.addActionListener(this);
        cont.add(btnContinue);

		setBounds(200, 200, 450, 300);
        setSize(329,178);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
        setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd){
			form frame = new form("ADD", CitizenList, new citizen());
			frame.setVisible(true);
			dispose();
			return;
		}
		else if(e.getSource() == btnContinue){
			Stack<citizen> stCenter1, stCenter2, stCenter3;
			Queue<citizen> qCenter1, qCenter2, qCenter3;
			
			stCenter1 = new Stack<>();
			stCenter2 = new Stack<>();
			stCenter3 = new Stack<>();
			
			qCenter1 = new LinkedList<>();
			qCenter2 = new LinkedList<>();
			qCenter3 = new LinkedList<>();

			System.out.println(CitizenList.getLast());
			LinkedList<citizen> completedList =  new LinkedList<>();
			
			int size = CitizenList.size();
			for (int i = 0; i < size; i++) {
				citizen person = CitizenList.removeLast();
				if(person.getAge() >= 18 & person.getAge() <= 30){
					if(person.getStat2ndDose() != null){
						completedList.add(person);
						continue;
					}
					else if(person.getStat1stDose() != null){
						qCenter1.add(person);
						continue;
					}
					else{
						stCenter1.add(person);
						continue;
					}
				}
				else if(person.getAge() >= 31 & person.getAge() <=49){
					if(person.getStat2ndDose() != null){
						completedList.add(person);
						continue;
					}
					else if(person.getStat1stDose() != null){
						qCenter2.add(person);
						continue;
					}
					else{
						stCenter2.add(person);
						continue;
					}
				}
				else if(person.getAge() >= 50){
					if(person.getStat2ndDose() != null){
						completedList.add(person);
						continue;
					}
					else if(person.getStat1stDose() != null){
						qCenter3.add(person);
						continue;
					}
					else{
						stCenter3.add(person);
						continue;
					}
				}
			}

			vaccination frame = new vaccination(stCenter1, stCenter2, stCenter3,qCenter1, qCenter2, qCenter3,completedList);
			frame.setVisible(true);
			dispose();
			return;
		}

		String ic = JOptionPane.showInputDialog(null, "Insert IC", "IC", JOptionPane.QUESTION_MESSAGE);
		int Person_num = -1;
		for (int i = 0; i < CitizenList.size(); i++) {
			if(CitizenList.get(i).getIc().equalsIgnoreCase(ic)) {
				Person_num = i;
				break;
			}
		}
		if(ic == null)
			return;

		if(Person_num == -1){
			JOptionPane.showMessageDialog(null, "Citizen with " + ic + " not existed","Not Found", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(e.getSource() == btnRemove){
			int reply = JOptionPane.showConfirmDialog(null, CitizenList.get(Person_num), "Are you sure to delete this citizen", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				CitizenList.remove(Person_num);
			}
		}
		else if (e.getSource() == btnUpdate) {
			form frame = new form("UPDATE", CitizenList, CitizenList.get(Person_num));
			frame.setVisible(true);
			dispose();
		}
	}
}