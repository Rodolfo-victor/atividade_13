package br.com.lista_atividade;

import java.io.*;
import java.util.*;

class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto [nome=" + nome + ", preco=" + preco + "]";
    }
}

public class SerializationUtil {
    public static void serialize(List<Produto> produtos, String fileName) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
        oos.writeObject(produtos);
        oos.close();
    }

    public static List<Produto> deserialize(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        List<Produto> produtos = (ArrayList<Produto>) ois.readObject();
        ois.close();
        return produtos;
    }
}

public class Produtos {
    public static void main(String[] args) {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Notebook", 1500.0));
        produtos.add(new Produto("Impressora", 300.0));
        produtos.add(new Produto("Mouse", 50.0));

        try {
            SerializationUtil.serialize(produtos, "produtos.dat");
            List<Produto> deserializedProdutos = SerializationUtil.deserialize("produtos.dat");
            System.out.println("Produtos:");
            for (Produto produto : deserializedProdutos) {
                System.out.println(produto);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
