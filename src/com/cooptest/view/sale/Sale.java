/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.cooptest.view.sale;

import com.cooptest.dao.CooperatedDao;
import com.cooptest.dao.PersonTypeDao;
import com.cooptest.dao.ProductDao;
import com.cooptest.dao.ProductGroupDao;
import com.cooptest.dao.TributeDao;
import com.cooptest.datatransfer.CooperatedDto;
import com.cooptest.datatransfer.PersonTypeDto;
import com.cooptest.datatransfer.ProductDto;
import com.cooptest.datatransfer.SaleItemsDto;
import com.cooptest.datatransfer.TributeDto;
import com.cooptest.model.Address;
import com.cooptest.model.Concept;
import com.cooptest.model.Cooperated;
import com.cooptest.model.Formula;
import com.cooptest.model.MeasureUnit;
import com.cooptest.model.Nationality;
import com.cooptest.model.PersonType;
import com.cooptest.model.Product;
import com.cooptest.model.ProductGroup;
import com.cooptest.model.SaleItems;
import com.cooptest.model.Tribute;
import com.cooptest.references.Frames;
import com.cooptest.view.selectcoop.SelectCoop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jose_
 */
public class Sale extends javax.swing.JFrame {
    private static CooperatedDao cooperatedDao;
    private static ProductDao productDao;
    private static ProductGroupDao productGroupDao;
    private static TributeDao tributeDao;
    
    /**
     * Creates new form Sale
     * @param cooperatedDao
     * @param productDao
     * @param productGroupDao
     */
    public Sale(CooperatedDao cooperatedDao, ProductDao productDao, ProductGroupDao productGroupDao, TributeDao tributeDao) {
        initComponents();
        
        Sale.cooperatedDao = cooperatedDao;
        Sale.productDao = productDao;
        Sale.productGroupDao = productGroupDao;
        Sale.tributeDao = tributeDao;
        
        listProducts();
        
        setLocationRelativeTo(null);
        
        setExtendedState(MAXIMIZED_BOTH);
        
        JPanelPortion.setVisible(false);
        
        jcbCalculatePortions.setVisible(false);
    }

    private void listProducts(){
        try{
            productDao.connect();
            
            DefaultTableModel defaultTableModel = (DefaultTableModel) tableProducts.getModel();
            
            tableProducts.setModel(defaultTableModel);

            ResultSet resultSet = productDao.findAll();
            
            defaultTableModel.setRowCount(0);
            while (resultSet.next()){
                String productName = resultSet.getString("pro.nome_comercial");
                String productCode = resultSet.getString("pro.cod_identificacao");
                String productGroup = resultSet.getString("gp.nome");
                String productMeasureUnit = resultSet.getString("um.simbolo");
                String productBasePrice = resultSet.getString("pro.preco_base");
                
                defaultTableModel.addRow(new Object[]{productName, productCode, productGroup, productMeasureUnit, productBasePrice});
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            cooperatedDao.closeConnection(cooperatedDao.getConnection());
        }
    }
    
    private ArrayList<ProductGroup> getAllProductGroup(){
        try{
            productGroupDao.connect();
            
            ResultSet resultSet = productGroupDao.findAll();
            
            ArrayList<ProductGroup> productGroups = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                productGroups.add(new ProductGroup(id, nome));
            }
            
            return productGroups;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        } finally {
            cooperatedDao.closeConnection(cooperatedDao.getConnection());
        }
    }
    
    private void listProductsById(int id){
        try{
            productDao.connect();
            
            DefaultTableModel defaultTableModel = (DefaultTableModel) tableProducts.getModel();
            
            tableProducts.setModel(defaultTableModel);

            ResultSet resultSet = productDao.findById(id);
            
            defaultTableModel.setRowCount(0);
            while (resultSet.next()){
                String productName = resultSet.getString("pro.nome_comercial");
                String productCode = resultSet.getString("pro.cod_identificacao");
                String productGroup = resultSet.getString("gp.nome");
                String productMeasureUnit = resultSet.getString("um.simbolo");
                String productBasePrice = resultSet.getString("pro.preco_base");
                
                defaultTableModel.addRow(new Object[]{productName, productCode, productGroup, productMeasureUnit, productBasePrice});
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        } finally {
            cooperatedDao.closeConnection(cooperatedDao.getConnection());
        }
    }
    
    private ArrayList<Product> getProductsByName(String _name){
        try{
            productDao.connect();
            
            ResultSet resultSet = productDao.findByName(_name);
            
            ArrayList<Product> products = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt("pro.id");
                ProductGroup productGroup = new ProductGroup(resultSet.getInt("gp.id"), resultSet.getString("gp.nome"));
                MeasureUnit measureUnit = new MeasureUnit(resultSet.getInt("um.id"), resultSet.getString("um.nome"), resultSet.getString("um.simbolo"), resultSet.getString("um.descricao"));
                Formula formula = new Formula(resultSet.getInt("fpa.id"), resultSet.getString("fpa.formula"), resultSet.getString("fpa.princ_ativo"));
                String identityCode = resultSet.getString("pro.cod_identificacao");
                String comercialName = resultSet.getString("pro.nome_comercial");
                String description = resultSet.getString("pro.descricao");
                Double precoBase = resultSet.getDouble("pro.preco_base");
                
                products.add(new Product(id, productGroup, measureUnit,formula, identityCode, comercialName, description, precoBase));
            }
            
            return products;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        } finally {
            cooperatedDao.closeConnection(cooperatedDao.getConnection());
        }
    }
    
//    private ArrayList<Cooperated> getCooperativeByName(int _id){
//        try{
//            cooperatedDao.connect();
//            
//            ResultSet resultSet = cooperatedDao.findById(_id);
//            
//            ArrayList<Cooperated> cooperative = new ArrayList<>();
//            while(resultSet.next()){
//                int id = resultSet.getInt("coo.id");
//                Nationality nationality = new Nationality(resultSet.getInt("nac.id"), resultSet.getString("nac.nome"));
//                Concept concept = new Concept(resultSet.getInt("con.id"), resultSet.getString("con.nome"), resultSet.getString("con.descricao"));
//                Address address = new Address(resultSet.getInt("en.id"), null, resultSet.getString("en.numero"), resultSet.getString("en.cep"), resultSet.getString("en.bairro"), resultSet.getString("en.rua"));
//                String nome = resultSet.getString("coo.nome");
//                String email = resultSet.getString("coo.email");
//                String telefone = resultSet.getString("coo.telefone");
//                
//                cooperative.add(new Cooperated(id, nationality, concept, address, nome, email, telefone));
//            }
//            
//            return cooperative;
//        }catch(SQLException e){
//            System.out.println(e.getMessage());
//            return null;
//        } finally {
//            cooperatedDao.closeConnection(cooperatedDao.getConnection());
//        }
//    }
    
    private ArrayList<Cooperated> getCooperativeByName(String _name){
        try{
            cooperatedDao.connect();
            
            ResultSet resultSet = cooperatedDao.findByName(_name);
            
            ArrayList<Cooperated> cooperative = new ArrayList<>();
            while(resultSet.next()){
                int id = resultSet.getInt("coo.id");
                Nationality nationality = new Nationality(resultSet.getInt("nac.id"), resultSet.getString("nac.nome"));
                Concept concept = new Concept(resultSet.getInt("con.id"), resultSet.getString("con.nome"), resultSet.getString("con.descricao"));
                Address address = new Address(resultSet.getInt("en.id"), null, resultSet.getString("en.numero"), resultSet.getString("en.cep"), resultSet.getString("en.bairro"), resultSet.getString("en.rua"));
                PersonType personType = new PersonType(resultSet.getInt("tp.id"), resultSet.getString("tp.nome"));
                String nome = resultSet.getString("coo.nome");
                String email = resultSet.getString("coo.email");
                String telefone = resultSet.getString("coo.telefone");
                
                cooperative.add(new Cooperated(id, nationality, concept, address, personType, nome, email, telefone));
            }
            
            return cooperative;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        } finally {
            cooperatedDao.closeConnection(cooperatedDao.getConnection());
        }
    }
    
    public void updateSelectedItemAmount(){
        tableItems.setValueAt(SaleItemsDto.getData().getQuantidade(), SaleItemsDto.getData().getId(), 5);
        
        Double unitPrice = Double.valueOf(String.valueOf(tableItems.getValueAt(SaleItemsDto.getData().getId(), 4)));
        Double amount = Double.valueOf(String.valueOf(tableItems.getValueAt(SaleItemsDto.getData().getId(), 5)));
        Double icmsFee = Double.valueOf(String.valueOf(tableItems.getValueAt(SaleItemsDto.getData().getId(), 6)).replace("%", ""));
        
        Double updatedValue = (unitPrice * amount);
        
        Double icmsFeePortion = updatedValue * (icmsFee / 100);
        
        updatedValue = updatedValue + icmsFeePortion;
        
        tableItems.setValueAt(updatedValue, SaleItemsDto.getData().getId(), 7);
        
        calculateTotalSaleValue();
        
        calculateFee();
    }
    
    public void popFilteredData(){
        if(CooperatedDto.getData().getConcept().getNome().equals("A")){
            jLDiscountByConcept.setText("5%");
        }else if(CooperatedDto.getData().getConcept().getNome().equals("B")){
            jLDiscountByConcept.setText("3%");
        }else{
            jLDiscountByConcept.setText("0%");
        }
        
        jlConcept.setText(CooperatedDto.getData().getConcept().getNome());
        jlCooperatedName.setText(CooperatedDto.getData().getNome());
        jlEmail.setText(CooperatedDto.getData().getEmail());
        jlNationality.setText(CooperatedDto.getData().getNationality().getNome());
        jlPersonType.setText(CooperatedDto.getData().getPersonType().getNome());
    }
    
    private void addProductForSale(){
        DefaultTableModel defaultTableModel = (DefaultTableModel) tableItems.getModel();
        
        tableItems.setModel(defaultTableModel);

        defaultTableModel.addRow(new Object[]{
            ProductDto.getData().getNomeComercial(),
            ProductDto.getData().getCodIdentificacao(),
            ProductDto.getData().getProductGroup().getNome(),
            ProductDto.getData().getMeasureUnit().getSimbolo(),
            ProductDto.getData().getPrecoBase(),
            1,
            TributeDto.getData().getIcms() + "%",
            (ProductDto.getData().getPrecoBase() + (ProductDto.getData().getPrecoBase() * (TributeDto.getData().getIcms() / 100))) * 1
        });
    }
    
    private boolean productIsAlreadyAddedForSale(){
        for(int i = 0; i < tableItems.getRowCount(); i++){
            String currentProduct = String.valueOf(tableItems.getValueAt(i, 1));
            
            if(currentProduct.equals(ProductDto.getData().getCodIdentificacao())){
                return true;
            }
        }
        return false;
    }
    
    private int countSelectedProductGroups(){
        ArrayList<ProductGroup> productGroups = getAllProductGroup();
        
        int countGroupsSelected = 0;
        for(ProductGroup productGroup : productGroups){
            for(int i = 0; i < tableItems.getRowCount(); i++){
                String currentProductGroup = String.valueOf(tableItems.getValueAt(i, 2));
                
                if(countGroupsSelected <= 5){
                    if(currentProductGroup.equals(productGroup.getNome())){
                        countGroupsSelected++;
                        break;
                    }
                }
            }
        }
        return countGroupsSelected;
    }
    
    private void calculateTotalSaleValue(){
        Double totalSaleValue = 0.0;
        
        for(int i = 0; i < tableItems.getRowCount(); i++){
            double currentValue = Double.parseDouble(String.valueOf(tableItems.getValueAt(i, 7)));

            totalSaleValue += currentValue;
        }
        
        Double discountByConceptPercent = Double.parseDouble(jLDiscountByConcept.getText().replace("%", "")) / 100;
        Double discountByProductGroupPercent = Double.valueOf(countSelectedProductGroups()) / 100;
        Double totalDiscountPerc = discountByConceptPercent + discountByProductGroupPercent;

        Double discountFraction = totalSaleValue * totalDiscountPerc;

        Double totalSaleValueWithDiscount = totalSaleValue - discountFraction;

        DecimalFormat df = new DecimalFormat("#,##0.00");
        
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(symbols);
        
        String formattedValue = df.format(totalSaleValueWithDiscount);
        
//        jLTotalSaleValue.setText("R$ " + totalSaleValueWithDiscount.toString().replace(".", ","));

        jLTotalSaleValue.setText("R$ " + formattedValue);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jlCooperatedName = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jlNationality = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jlPersonType = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jlConcept = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jlEmail = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProducts = new javax.swing.JTable();
        jtfProductSearch = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jtfCooperatedNameSearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableItems = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jcbUfFrom = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jcbUfTo = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLDiscountByProductGroup = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLDiscountByConcept = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLTotalSaleValue = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLTotalFee = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLFeePlusTotalValue = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jcbPaymentMethod = new javax.swing.JComboBox<>();
        JPanelPortion = new javax.swing.JPanel();
        jtfPortions = new javax.swing.JTextField();
        jcbCalculatePortions = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do cooperado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Nome"));

        jlCooperatedName.setText("-");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlCooperatedName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlCooperatedName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Nacionalidade"));

        jlNationality.setText("-");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlNationality, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlNationality)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de pessoa"));

        jlPersonType.setText("-");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlPersonType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlPersonType)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("Conceito"));

        jlConcept.setFont(new java.awt.Font("Segoe UI", 1, 100)); // NOI18N
        jlConcept.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlConcept.setText("-");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlConcept, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlConcept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder("E-mail"));

        jlEmail.setText("-");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlEmail)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tableProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Produto", "Código", "Grupo", "Unidade de medida", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProducts);

        jtfProductSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfProductSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfProductSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfProductSearchKeyTyped(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Produto", "Código", "Grupo", "Unidade de medida", "Preço" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfProductSearch)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfProductSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar cooperado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtfCooperatedNameSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfCooperatedNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Itens da venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tableItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Código", "Grupo", "Unidade de medida", "Preço unitário", "Quantidade", "ICMS", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableItemsMouseClicked(evt);
            }
        });
        tableItems.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableItemsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableItemsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableItemsKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tableItems);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Destino / Origem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Origem"));

        jcbUfFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PR", "SC", "MS" }));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbUfFrom, 0, 144, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jcbUfFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Destino"));

        jcbUfTo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PR", "SC", "MS" }));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbUfTo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jcbUfTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descontos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Por grupo de produtos"));

        jLDiscountByProductGroup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDiscountByProductGroup.setText("0%");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLDiscountByProductGroup, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLDiscountByProductGroup)
                .addContainerGap())
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Por conceito"));

        jLDiscountByConcept.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDiscountByConcept.setText("0%");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLDiscountByConcept, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLDiscountByConcept)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total da venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor total"));

        jLTotalSaleValue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLTotalSaleValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLTotalSaleValue.setText("R$ 0,00");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLTotalSaleValue, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLTotalSaleValue)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Juros (R$)"));

        jLTotalFee.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLTotalFee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLTotalFee.setText("R$ 0,00");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLTotalFee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLTotalFee)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Total + juros"));

        jLFeePlusTotalValue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLFeePlusTotalValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLFeePlusTotalValue.setText("R$ 0,00");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLFeePlusTotalValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLFeePlusTotalValue)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de pagamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Método"));

        jcbPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "À vista", "A prazo" }));
        jcbPaymentMethod.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbPaymentMethodItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbPaymentMethod, 0, 201, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPanelPortion.setBorder(javax.swing.BorderFactory.createTitledBorder("Núm. parcelas (em meses)"));

        javax.swing.GroupLayout JPanelPortionLayout = new javax.swing.GroupLayout(JPanelPortion);
        JPanelPortion.setLayout(JPanelPortionLayout);
        JPanelPortionLayout.setHorizontalGroup(
            JPanelPortionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPortionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtfPortions)
                .addContainerGap())
        );
        JPanelPortionLayout.setVerticalGroup(
            JPanelPortionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelPortionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtfPortions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jcbCalculatePortions.setText("Calcular");
        jcbCalculatePortions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbCalculatePortionsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPanelPortion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbCalculatePortions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPanelPortion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbCalculatePortions)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ArrayList<Cooperated> cooperative = getCooperativeByName(jtfCooperatedNameSearch.getText());
        CooperatedDto.setDatas(cooperative);
        
        PersonTypeDao personTypeDao = new PersonTypeDao();
        
        if (Frames.selectCoop == null)
            Frames.selectCoop = new SelectCoop(personTypeDao);
        
        Frames.selectCoop.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Frames.sale = null;
        PersonTypeDto.setData(null);
        CooperatedDto.setData(null);
        ProductDto.setData(null);
        SaleItemsDto.setData(null);
        TributeDto.setData(null);
    }//GEN-LAST:event_formWindowClosed

    private void jtfProductSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfProductSearchKeyTyped
        
    }//GEN-LAST:event_jtfProductSearchKeyTyped

    private void jtfProductSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfProductSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfProductSearchKeyPressed

    private void jtfProductSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfProductSearchKeyReleased
        ArrayList<Product> products = getProductsByName(jtfProductSearch.getText());
        
        DefaultTableModel defaultTableModel = (DefaultTableModel) tableProducts.getModel();
        
        tableProducts.setModel(defaultTableModel);

        defaultTableModel.setRowCount(0);
        
        for (Product product : products) {
            String commercialName = product.getNomeComercial();
            String identityCode = product.getCodIdentificacao();
            String productGroup = product.getProductGroup().getNome();
            String measureUnit = product.getMeasureUnit().getSimbolo();
            Double basePrice = product.getPrecoBase();

            defaultTableModel.addRow(new Object[]{commercialName, identityCode, productGroup, measureUnit, basePrice});
        }
    }//GEN-LAST:event_jtfProductSearchKeyReleased

    private void tableProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductsMouseClicked
        if (evt.getClickCount() == 2){
            try{
                productGroupDao.connect();
                tributeDao.connect();
                
                int row = tableProducts.getSelectedRow();

                Product product = new Product();

                product.setNomeComercial(String.valueOf(tableProducts.getValueAt(row, 0)));
                product.setCodIdentificacao(String.valueOf(tableProducts.getValueAt(row, 1)));

                String productGroupName = String.valueOf(tableProducts.getValueAt(row, 2));
                ResultSet resultSet = productGroupDao.findByName(productGroupName);
                resultSet.next();
                product.setProductGroup(new ProductGroup(resultSet.getInt("id"), productGroupName));
                
                product.setMeasureUnit(new MeasureUnit(String.valueOf(tableProducts.getValueAt(row, 3))));
                product.setPrecoBase(Double.valueOf(String.valueOf(tableProducts.getValueAt(row, 4))));
                
                ProductDto.setData(product);

                if(PersonTypeDto.getData() == null){
                    JOptionPane.showMessageDialog(null, "Cooperado não selecionado.");
                    return;
                }
                
                int idUfFrom = jcbUfFrom.getSelectedIndex() + 1;
                int idUfTo = jcbUfTo.getSelectedIndex() + 1;
                int idProductGroup = product.getProductGroup().getId();
                int idPersonType = PersonTypeDto.getData().getId();
                

                ResultSet resultSet2 = tributeDao.findByTributeCriteria(idUfFrom, idUfTo, idProductGroup, idPersonType);
                if(resultSet2.next()){
//                  JOptionPane.showMessageDialog(null, resultSet2.getInt("icms"));
                    Double icms = resultSet2.getDouble("icms");
                    TributeDto.setData(new Tribute(icms));
                }else{
                    JOptionPane.showMessageDialog(null, 
                            "O produto não será adicionado pois a seguinte situação não está prevista no cadastro de tributação:\n\n" +
                            "Origem: " + jcbUfFrom.getItemAt(jcbUfFrom.getSelectedIndex()) + "\n" +
                            "Destino: " + jcbUfFrom.getItemAt(jcbUfFrom.getSelectedIndex()) + "\n" +
                            "Grupo de produto: " + product.getProductGroup().getNome() + "\n" +
                            "Tipo de pessoa: " + PersonTypeDto.getData().getNome()
                    );
                    return;
                }
                
                if(!productIsAlreadyAddedForSale()){
                    addProductForSale();
                }else{
                    JOptionPane.showMessageDialog(null, "O produto " + ProductDto.getData().getCodIdentificacao() + " já foi adicionado. Altere a quantidade na tabela de itens.");
                }
                
//                JOptionPane.showMessageDialog(null, countSelectedProductGroups());
                
                jLDiscountByProductGroup.setText(countSelectedProductGroups() + "%");
                
                calculateTotalSaleValue();
            }catch(SQLException e){
                System.err.println(e.getMessage());
            } finally {
                productGroupDao.closeConnection(productGroupDao.getConnection());
                tributeDao.closeConnection(tributeDao.getConnection());
            }
        }
    }//GEN-LAST:event_tableProductsMouseClicked

    private void tableItemsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableItemsKeyReleased
        
    }//GEN-LAST:event_tableItemsKeyReleased

    private void tableItemsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableItemsKeyTyped
        
    }//GEN-LAST:event_tableItemsKeyTyped

    private void tableItemsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableItemsKeyPressed
        
    }//GEN-LAST:event_tableItemsKeyPressed

    private void tableItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableItemsMouseClicked
        int row = tableItems.rowAtPoint(evt.getPoint());
        
        int currentAmount = Integer.parseInt(String.valueOf(tableItems.getValueAt(row, 5)));
        
        SaleItemsDto.setData(new SaleItems(row, currentAmount));
        
        if (evt.getButton() == MouseEvent.BUTTON3){
            JPopupMenu popupMenu = new JPopupMenu();

            // Exemplo de itens de menu dinâmicos
            JMenuItem updateAmount = new JMenuItem("Alterar quantidade");
//            JMenuItem removeProduct = new JMenuItem("Remover");

            // Adicionando ação aos itens de menu
            updateAmount.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (Frames.updateProductAmount == null)
                        Frames.updateProductAmount = new UpdateProductAmount();

                    Frames.updateProductAmount.setVisible(true);
                }
            });

//            removeProduct.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                
//                }
//            });

            // Adicionando itens ao menu popup
            popupMenu.add(updateAmount);
//            popupMenu.add(removeProduct);
            
            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tableItemsMouseClicked

    private void jcbPaymentMethodItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbPaymentMethodItemStateChanged
        switch (jcbPaymentMethod.getSelectedIndex()) {
            case 0:
                JPanelPortion.setVisible(false);
                jcbCalculatePortions.setVisible(false);
                jLTotalFee.setText("R$ 0,00");
                jLFeePlusTotalValue.setText("R$ 0,00");
                break;
            case 1:
                JPanelPortion.setVisible(true);
                jcbCalculatePortions.setVisible(true);
                
                try{
                    if(Integer.parseInt(jtfPortions.getText()) > 0){
                        calculateFee();
                    }
                }catch(NumberFormatException e){
                    
                }
                
                break;
        }
    }//GEN-LAST:event_jcbPaymentMethodItemStateChanged

    private void jcbCalculatePortionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbCalculatePortionsActionPerformed
        calculateFee();
    }//GEN-LAST:event_jcbCalculatePortionsActionPerformed

    private void calculateFee(){
        LocalDate dataCompra = LocalDate.of(2024, 6, 13);
        int numParcelas = Integer.parseInt(jtfPortions.getText());
        
        LocalDate dataFinal = dataCompra.plusMonths(numParcelas);

        Set<LocalDate> feriados = obterFeriados();
        long diasUteis = calcularDiasUteis(dataCompra, dataFinal, feriados);

//        JOptionPane.showMessageDialog(null, diasUteis);
        
        Double totalSaleValue = Double.valueOf(jLTotalSaleValue.getText().replace("R$", "").replace(".", "").replace(",", "."));
        Double totalFee = (totalSaleValue * (Math.pow(1 + (0.05 / 100), diasUteis) - 1));
        
        DecimalFormat df = new DecimalFormat("#,##0.00");
        
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        df.setDecimalFormatSymbols(symbols);
        
        String formattedFee = df.format(totalFee);
        
        jLTotalFee.setText("R$ " + formattedFee);

        Double feePlusTotalValue = totalSaleValue + totalFee;
        String formattedTotalValue = df.format(feePlusTotalValue);
        
        jLFeePlusTotalValue.setText("R$ " + formattedTotalValue);
    }
    
    public static long calcularDiasUteis(LocalDate startDate, LocalDate endDate, Set<LocalDate> feriados) {
        long diasUteis = 0;

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (isDiaUtil(date, feriados)) {
                diasUteis++;
            }
        }

        return diasUteis;
    }

    public static boolean isDiaUtil(LocalDate date, Set<LocalDate> feriados) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return false;
        }
        
        if (feriados.contains(date)) {
            return false;
        }
        return true;
    }

    public static Set<LocalDate> obterFeriados() {
        Set<LocalDate> feriados = new HashSet<>();
        feriados.add(LocalDate.of(2024, 1, 1));  // Ano Novo
        feriados.add(LocalDate.of(2024, 4, 21)); // Tiradentes
        feriados.add(LocalDate.of(2024, 5, 1));  // Dia do Trabalho
        feriados.add(LocalDate.of(2024, 9, 7));  // Independência do Brasil
        feriados.add(LocalDate.of(2024, 10, 12)); // Nossa Senhora Aparecida
        feriados.add(LocalDate.of(2024, 11, 2));  // Finados
        feriados.add(LocalDate.of(2024, 11, 15)); // Proclamação da República
        feriados.add(LocalDate.of(2024, 12, 25)); // Natal
        // Adicione outros feriados aqui, conforme necessário
        return feriados;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Sale(cooperatedDao, productDao, productGroupDao, tributeDao).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelPortion;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLDiscountByConcept;
    private javax.swing.JLabel jLDiscountByProductGroup;
    private javax.swing.JLabel jLFeePlusTotalValue;
    private javax.swing.JLabel jLTotalFee;
    private javax.swing.JLabel jLTotalSaleValue;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jcbCalculatePortions;
    private javax.swing.JComboBox<String> jcbPaymentMethod;
    private javax.swing.JComboBox<String> jcbUfFrom;
    private javax.swing.JComboBox<String> jcbUfTo;
    private javax.swing.JLabel jlConcept;
    private javax.swing.JLabel jlCooperatedName;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlNationality;
    private javax.swing.JLabel jlPersonType;
    private javax.swing.JTextField jtfCooperatedNameSearch;
    private javax.swing.JTextField jtfPortions;
    private javax.swing.JTextField jtfProductSearch;
    private javax.swing.JTable tableItems;
    private javax.swing.JTable tableProducts;
    // End of variables declaration//GEN-END:variables
}
