import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.swing.LayoutStyle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;

public class DisplayCourseInfo extends Application implements EventHandler<ActionEvent>{
	static ArrayList<String> students = new ArrayList<String>();
	static ArrayList<String> grades = new ArrayList<String>();
	Student student = new Student();
	public Text text;
	Stage window;
	Scene scene;
	public TextField tf = new TextField();
	Button button;
	public VBox layout;
	ListView<String> view;
	static boolean studentsOkay;
	public double checkSum = Course.getSum / 3;
	
	ListView<String> view1;
	File loadfromFile = new File("students.txt.txt");
	File loadfromFile2 =new File("grades.txt.txt");
	private Course course = new Course();
	

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	public static void main(String[] args) throws IOException 
	{
	System.out.println("Please enter a name for a file to load students");
	Scanner input = new Scanner(System.in);
	Course.loadStudents(input.next());
	System.out.println("Please enter a name for a file to load for grades");
	Course.loadGrades(input.next());
	input.close();
	
	InputStream is1 = new FileInputStream("grades.txt.txt");
	BufferedReader buf1 = new BufferedReader(new InputStreamReader(is1));
	String line1 = buf1.readLine();
	StringBuilder sb1 = new StringBuilder();
    InputStream is = new FileInputStream("students.txt.txt");
    BufferedReader buf = new BufferedReader(new InputStreamReader(is));
    String line = buf.readLine();
    StringBuilder sb = new StringBuilder();
    while (buf.ready())

    {
      
      line = buf.readLine();
      students.add(line); 

    }	
    
    while(line1 != null)
    {
    	line1 = buf1.readLine();
    	grades.add(line1);
    	buf.close();

    	 	
    }
    launch(args);  
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{

		window = primaryStage;
		StackPane stack = new StackPane();
		List<Label> labels = grades
			     .stream()
			     .map( Label::new )
			     .collect(Collectors.toList());	
		String text1 = "Grades";
		stack.setAlignment(Pos.TOP_RIGHT);
		stack.setPadding(new Insets(20, 20, 20, 20));
		window.setTitle("Display Students");
		button = new Button("Course Average");
		view = new ListView<String>();
		view1 = new ListView<String>();
		Text text2 = new Text("Student ID, Student Name, Grade");
		String text = ("Student ID, Student Name");
		view.getItems().add(text);
		view.setOnMouseClicked(e ->{
		
		
			
			String s = grades.get(view.getSelectionModel().getSelectedIndex());
			tf.setText(s);
		
		
	
			
			
		});
		view1.getItems().add(text1);
		view1.getItems().addAll(grades);
		view.getItems().addAll(students);
		button.setOnAction(this);
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20,20,20,20));
		layout.getChildren().addAll(view,button);
		layout.getChildren().addAll(tf);
		layout.getChildren().addAll(view1);
		layout.getChildren().add(stack);
		tf.setPadding(new Insets(20,20,20,20));
		tf.setAlignment(Pos.TOP_RIGHT);
		scene = new Scene(layout, 300, 250);
		window.setScene(scene);
		window.show();
	}
	
	public void handleMouseClick(MouseEvent arg0) {
	    System.out.println("clicked on ");
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		
		Course.calculateAverage();
		tf.setText("Course Average is:" + Course.sum);
		
	}
	
}






