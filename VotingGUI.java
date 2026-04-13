import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class VotingGUI extends JFrame implements ActionListener {

    private Map<String, Integer> candidates = new HashMap<>();
    private Map<String, String> voteRecord = new HashMap<>();

    private JTextField voterField;
    private JTextArea resultArea;

    private JButton voteAlice, voteBob, voteCharlie, showResults, showDetailed;

    public VotingGUI() {
        setTitle("Online Voting System");
        setSize(450, 450);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Candidates
        candidates.put("Alice", 0);
        candidates.put("Bob", 0);
        candidates.put("Charlie", 0);

        // UI
        add(new JLabel("Enter Voter ID:"));
        voterField = new JTextField(15);
        add(voterField);

        voteAlice = new JButton("Vote Alice");
        voteBob = new JButton("Vote Bob");
        voteCharlie = new JButton("Vote Charlie");
        showResults = new JButton("Show Results");
        showDetailed = new JButton("Show Vote Records");

        add(voteAlice);
        add(voteBob);
        add(voteCharlie);
        add(showResults);
        add(showDetailed);

        resultArea = new JTextArea(12, 35);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        // Listeners
        voteAlice.addActionListener(this);
        voteBob.addActionListener(this);
        voteCharlie.addActionListener(this);
        showResults.addActionListener(this);
        showDetailed.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String voterId = voterField.getText().trim();

        if (voterId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Voter ID!");
            return;
        }

        // Prevent duplicate vote
        if (voteRecord.containsKey(voterId)) {
            JOptionPane.showMessageDialog(this, "You already voted!");
            return;
        }

        if (e.getSource() == voteAlice) {
            recordVote(voterId, "Alice");
        } 
        else if (e.getSource() == voteBob) {
            recordVote(voterId, "Bob");
        } 
        else if (e.getSource() == voteCharlie) {
            recordVote(voterId, "Charlie");
        } 
        else if (e.getSource() == showResults) {
            displayResults();
        } 
        else if (e.getSource() == showDetailed) {
            displayVoteRecords();
        }
    }

    private void recordVote(String voterId, String candidate) {
        candidates.put(candidate, candidates.get(candidate) + 1);
        voteRecord.put(voterId, candidate);

        JOptionPane.showMessageDialog(this, "Vote Recorded for " + candidate);
        voterField.setText("");
    }

    private void displayResults() {
        resultArea.setText("📊 Vote Count:\n\n");
        for (String name : candidates.keySet()) {
            resultArea.append(name + " : " + candidates.get(name) + " votes\n");
        }
    }

    // 🔥 NEW FEATURE
    private void displayVoteRecords() {
        resultArea.setText("🧾 Detailed Vote Records:\n\n");
        for (String voter : voteRecord.keySet()) {
            resultArea.append("Voter ID: " + voter + " → " + voteRecord.get(voter) + "\n");
        }
    }

    public static void main(String[] args) {
        new VotingGUI();
    }
}