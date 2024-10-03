import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LibrarySystemAWT {
    private Frame mainFrame;
    private Label statusLabel;
    private TextField titleField, authorField, isbnField;
    private List bookList;
    private ArrayList<Book> books;

    public LibrarySystemAWT() {
        books = new ArrayList<>();
        prepareGUI();
    }

    public static void main(String[] args) {
        LibrarySystemAWT librarySystem = new LibrarySystemAWT();
        librarySystem.showEventDemo();
    }

    private void prepareGUI() {
        // Frame Setup
        mainFrame = new Frame("Library Resource Administrator");
        mainFrame.setSize(500, 400);
        mainFrame.setLayout(new BorderLayout());

        // Status Label
        statusLabel = new Label();
        statusLabel.setAlignment(Label.CENTER);
        statusLabel.setText("Status: Ready");

        // Book List Display
        bookList = new List();
        Panel listPanel = new Panel();
        listPanel.setLayout(new BorderLayout());
        listPanel.add(new Label("Books:"), BorderLayout.NORTH);
        listPanel.add(bookList, BorderLayout.CENTER);

        // Input Fields
        Panel controlPanel = new Panel();
        controlPanel.setLayout(new GridLayout(5, 2));

        controlPanel.add(new Label("Title:"));
        titleField = new TextField();
        controlPanel.add(titleField);

        controlPanel.add(new Label("Author:"));
        authorField = new TextField();
        controlPanel.add(authorField);

        controlPanel.add(new Label("ISBN:"));
        isbnField = new TextField();
        controlPanel.add(isbnField);

        // Buttons
        Button addButton = new Button("Add Book");
        Button deleteButton = new Button("Delete Book");
        Button searchButton = new Button("Search Book");

        controlPanel.add(addButton);
        controlPanel.add(deleteButton);
        controlPanel.add(searchButton);

        // Main Frame Assembly
        mainFrame.add(controlPanel, BorderLayout.NORTH);
        mainFrame.add(listPanel, BorderLayout.CENTER);
        mainFrame.add(statusLabel, BorderLayout.SOUTH);

        mainFrame.setVisible(true);

        // Event Handlers
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBook();
            }
        });

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();

        if (!title.isEmpty() && !author.isEmpty() && !isbn.isEmpty()) {
            Book newBook = new Book(title, author, isbn);
            books.add(newBook);
            updateBookList();
            statusLabel.setText("Book added: " + title);
            clearFields();
        } else {
            statusLabel.setText("Error: All fields must be filled");
        }
    }

    private void deleteBook() {
        int selectedIndex = bookList.getSelectedIndex();
        if (selectedIndex >= 0) {
            books.remove(selectedIndex);
            updateBookList();
            statusLabel.setText("Book deleted");
        } else {
            statusLabel.setText("Error: No book selected");
        }
    }

    private void searchBook() {
        String searchTitle = titleField.getText();
        if (searchTitle.isEmpty()) {
            statusLabel.setText("Error: Enter a title to search");
            return;
        }

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(searchTitle)) {
                found = true;
                statusLabel.setText("Found: " + book);
                break;
            }
        }

        if (!found) {
            statusLabel.setText("Book not found");
        }
    }

    private void updateBookList() {
        bookList.removeAll();
        for (Book book : books) {
            bookList.add(book.getTitle());
        }
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
    }

    private void showEventDemo() {
        statusLabel.setText("Ready for operations");
    }

    class Book {
        private String title, author, isbn;

        public Book(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getIsbn() {
            return isbn;
        }

        @Override
        public String toString() {
            return title + " by " + author + " (ISBN: " + isbn + ")";
        }
    }
}