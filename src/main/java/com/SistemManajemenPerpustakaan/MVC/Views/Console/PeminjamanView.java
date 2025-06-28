package com.SistemManajemenPerpustakaan.MVC.Views.Console;

import com.SistemManajemenPerpustakaan.MVC.Controllers.PeminjamanController;
import com.SistemManajemenPerpustakaan.Utils.InformationPrinter;

import java.util.Scanner;

public class PeminjamanView {
    //OBJECT
    private static Scanner input = new Scanner(System.in);

    //METHOD
    //READ
    public static void detailPeminjaman(){
        while (true){
            if (isPeminjamanKosong()) return;

            InformationPrinter.tampilkanObjek(PeminjamanController.AmbilSemuaPeminjaman(), "Peminjaman yang tersedia", "nama");
            String idPeminjaman = AmbilIdPeminjaman("tampilkan detailnya");

            if (idPeminjaman == null) return;

            InformationPrinter.tampilkanAtributDenganNilai(PeminjamanController.ambilPeminjaman(idPeminjaman),
                    "", 0, "id");

            System.out.print("\nApakah anda ingin melihat detail peminjaman lainnya? (y/n): ");
            String x = input.nextLine();
            if (!(x.toUpperCase().equals("Y"))) break;
        }
    }

    //===TOOLS===
    public static String AmbilIdPeminjaman(String aksi){
        String y;
        int x;
        System.out.print("\nPilih nomor peminjaman yang ingin di"+aksi+": "); x = input.nextInt() - 1; input.nextLine();
        if (x >= PeminjamanController.AmbilSemuaPeminjaman().size() || x < 0) {
            System.out.print("pilihan tidak ada!\n");
            y = null;
        }
        else {
            y = PeminjamanController.AmbilSemuaPeminjaman().get(x).getId();
        }
        return y;
    }

    public static Boolean isPeminjamanKosong(){
        if (PeminjamanController.AmbilSemuaPeminjaman().isEmpty()){
            System.out.print("Peminjaman kosong!\n");
            return true;
        }else {
            return false;
        }
    }
}
