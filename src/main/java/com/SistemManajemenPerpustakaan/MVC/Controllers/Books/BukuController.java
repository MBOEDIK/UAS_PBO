package com.SistemManajemenPerpustakaan.MVC.Controllers.Books;

import com.SistemManajemenPerpustakaan.DTOs.Books.BukuDTO;
import com.SistemManajemenPerpustakaan.MVC.Controllers.Factory.DTOtoModel;
import com.SistemManajemenPerpustakaan.MVC.Models.Book.Buku;
import com.SistemManajemenPerpustakaan.Repositories.BukuRepository;

public class BukuController {

    //CREATE
    public void tambahBuku(BukuDTO dto){
        Buku buku = DTOtoModel.toBuku(dto);

        switch (buku.getJenis()){
            case "Jurnal Ilmiah": BukuRepository.tambah(buku);
        }
    }

    //READ

    //UPDATE

    //DELETE
}
