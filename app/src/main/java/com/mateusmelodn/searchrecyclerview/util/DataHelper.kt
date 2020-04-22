package com.mateusmelodn.searchrecyclerview.util

import com.mateusmelodn.searchrecyclerview.model.Product

class DataHelper {
    companion object {
        fun generateFakeProducts(): Array<Product> {
            return arrayOf(
                Product("1", "Geladeira","R$ 1,00"),
                Product("2", "Fogão","R$ 2,00"),
                Product("3", "Gás","R$ 3,00"),
                Product("4", "TV", "R$ 4,00"),
                Product("5", "Computador","R$ 5,00"),
                Product("6", "Tela","R$ 6,00"),
                Product("7", "Ventilador","R$ 7,00"),
                Product("8", "Cama","R$ 8,00"),
                Product("9", "Bicicleta","R$ 9,00"),
                Product("10", "Janela","R$ 10,00"),
                Product("11", "Carro","R$ 11,00"),
                Product("12", "Guarda-roupas","R$ 12,00"),
                Product("13", "Celular","R$ 13,00"),
                Product("14", "Fone de ouvido","R$ 14,00"),
                Product("15", "Sapato","R$ 15,00"),
                Product("16", "Blusa","R$ 16,00"),
                Product("17", "Cadeira","R$ 17,00"),
                Product("18", "Mesa","R$ 18,00"),
                Product("19", "Bolsa","R$ 19,00"),
                Product("20", "Livros","R$ 20,00"),
                Product("21", "Impressora","R$ 21,00")
                )
        }
    }
}