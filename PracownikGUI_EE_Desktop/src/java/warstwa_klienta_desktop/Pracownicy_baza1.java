/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_klienta_desktop;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Kruczkiewicz
 */
public class Pracownicy_baza1 extends JPanel {

    private JTable tabela_pracownikow;	//komponent typu tabela do wyświetlania danych pracowników
    MyTableModel model;			//model widoku
    JComboBox lista_pracownikow;		//lista wyswietlajaca dane pracowników

    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        model = new MyTableModel();		                    //tworzenie modelu danych tabeli
        table_content();//wypelnienie danymi pracownikow tabeli
        tabela_pracownikow = new JTable(model);	                   // utworzenie tabeli i przekazanie jej modelu z danymi pracownikow
        tabela_pracownikow.setPreferredScrollableViewportSize(new Dimension(800, 100));
        tabela_pracownikow.setFillsViewportHeight(true);
        tabela_pracownikow.getSelectionModel().addListSelectionListener(new RowListener());            //dodanie słuchacza zdarzen do obslugi 						             //zmiany wyboru wiersza 
        add(new JScrollPane(tabela_pracownikow));			             //dodanie panelu przewijania tabdli 
        JLabel lpracownicy = new JLabel("Pracownicy");
        add(lpracownicy);
        lista_pracownikow = new JComboBox();
        JLabel akcja = new JLabel("Akcja do wykonania:");
        ButtonGroup akcjaGroup = new ButtonGroup();
        
        //radio buttony odpowiadajace za wybor akcji:
        JRadioButton usun = new JRadioButton("usuń");
        JRadioButton modyfikuj = new JRadioButton("modyfikuj");
        JRadioButton wyszukaj = new JRadioButton("wyszukaj");
        
        //radio buttony odpowiadajace za wybor kryterium:
        JLabel kryterium = new JLabel("Kryterium:");
        ButtonGroup kryteriumGroup = new ButtonGroup();
        
        JRadioButton imie = new JRadioButton("imię");
        JRadioButton nazwisko = new JRadioButton("nazwisko");
        JRadioButton numerTelefonu = new JRadioButton("nr. telefonu");
        JRadioButton adres = new JRadioButton("adres");
        JRadioButton miasto = new JRadioButton("miasto");
        JRadioButton stanowisko = new JRadioButton("stanowisko");
        JRadioButton pensja = new JRadioButton("pensja");
        JRadioButton dataRozpoczeciaPracy = new JRadioButton("data rozp. pracy");
        
        add(lista_pracownikow);
        
        add(akcja);
        akcjaGroup.add(usun);
        akcjaGroup.add(modyfikuj);
        akcjaGroup.add(wyszukaj);
        
        add(usun);
        add(modyfikuj);
        add(wyszukaj);
        
        add(kryterium);
        kryteriumGroup.add(imie);
        kryteriumGroup.add(nazwisko);
        kryteriumGroup.add(numerTelefonu);
        kryteriumGroup.add(adres);
        kryteriumGroup.add(miasto);
        kryteriumGroup.add(stanowisko);
        kryteriumGroup.add(pensja);
        kryteriumGroup.add(dataRozpoczeciaPracy);
        
        add(imie);
        add(nazwisko);
        add(numerTelefonu);
        add(adres);
        add(miasto);
        add(stanowisko);
        add(pensja);
        add(dataRozpoczeciaPracy);
        
    }

    void table_content() {				//wypelnienie tablicy typu JTable danymi pracownikow
        ArrayList<ArrayList<String>> pracownicy = GUI_main.getFacade().items();
        model.setData(pracownicy);
    }

    private void list_content(ArrayList<ArrayList<String>> col, JComboBox list) {
        ArrayList<String> s;				//wypelnienie listy typu JComboBox danymi pracownikow
        list.removeAllItems();
        Iterator<ArrayList<String>> iterator = col.iterator();
        while (iterator.hasNext()) {
            s = iterator.next();
            list.addItem(s);
        }
    }

    void print_pracownicy() {		//metoda wypelniajaca listę typu JComboBox danymi pracownikow pobranymi metodą items. 
        ArrayList<ArrayList<String>> help3 = GUI_main.getFacade().items();	            // pobranie danych pracownikow metoda items
        if (help3 != null) {
            list_content(help3, lista_pracownikow);			           //wypelnianie listy typu JComboBox danymi pracownikow
        }
    }

    private class RowListener implements ListSelectionListener {      	//klasa wewnetrzna do obslugi zdarzen zmiany wyboru wiersza tabeli

        @Override
        public void valueChanged(ListSelectionEvent event) {		//metoda do obsługi zdarzenia zmiany  wybranego wiersza tabeli 
            if (event.getValueIsAdjusting()) {			//za pomocą klikniecia myszy na wybrany rowek
                return;
            }
            print_pracownicy();		// po zmianie wiersza wykonanie metody wypelniajacej listę typu JComboBox danymi pracownikow  
        }
    }

    class MyTableModel extends AbstractTableModel {	//klasa wewnetrzna reprezentujaca model danych obiektu typu JTable

        private final String[] columnNames = {"Id pracownika", "Nazwa", "Cena", //nazwy kolumn tabeli
            "Promocja", "Data", "Cena brutto", "Data przydatnosci", "Producent", "Ilosc"};
        private ArrayList<ArrayList<String>> data;	//dane tabeli-kazdy element zawiera elementy wiersza, jako kolekcja  lancuchow

        public void setData(ArrayList<ArrayList<String>> val) { 			//wstawienie danych modelu
            data = val;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length; 				//liczba kolumn
        }

        @Override
        public int getRowCount() {
            return data.size();					//liczba rowkow
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row).get(col);		//pobrane elementu z podanej komorki tabeli w wieszu row i kolumnie col
        }
    }
}