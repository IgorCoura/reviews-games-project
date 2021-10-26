package br.com.imt.interfaces

import br.com.imt.models.Games

interface IDaoGames {
     fun insert(obj: Games);
     fun update(obj: Games);
     fun delete(id: Int);
     fun get(id: Int): Games;
     fun getAll(): List<Games>;
}