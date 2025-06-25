package com.SistemManajemenPerpustakaan.MVC.Views.console.SubMenuAdmin;

import com.SistemManajemenPerpustakaan.MVC.Models.PenyimpananData;

import java.util.Scanner;

public class SubMenuDetailPeminjaman {

    //ATTRIBUTES
    private static String inputStr;
    private static Boolean inputBool;

    //OBJECTS
    static Scanner input = new Scanner(System.in);

    //METHODS
    //============================================================================================================================================================================================================

    public static void menuDetailPeminjaman(){
        int inputInt, subInputInt;
        String inputStr;

        loop : while (true){
            if (PenyimpananData.getDataPeminjaman().size() == 0){
                System.out.print("Peminjaman kosong!\n");
                break loop;
            }

            System.out.print("\nDetail Peminjaman ->");
            for (int i = 0; i < PenyimpananData.getDataPeminjaman().size(); i++){
                System.out.print("\n      "+(i + 1)+". "+PenyimpananData.getDataPeminjaman().get(i).getTanggalPinjam());
            }
            System.out.print("\nPilih index peminjaman yang ingin ditampilkan detail informasinya: "); inputInt = input.nextInt() - 1; input.nextLine();

            if (inputInt > PenyimpananData.getDataPeminjaman().size() || inputInt < 0) {
                System.out.print("pilihan tidak ada!\n");
                continue;
            }

            System.out.print("" +
                    "      1. Id Peminjaman: "+PenyimpananData.getDataPeminjaman().get(inputInt).getIdPeminjaman()+"" +
                    "\n      2. Id Anggota: "+PenyimpananData.getDataPeminjaman().get(inputInt).getIdAnggota()+"" +
                    "\n      3. Kode Buku: "+PenyimpananData.getDataPeminjaman().get(inputInt).getKodebuku()+"" +
                    "\n      4. Tanggal Pinjam: "+PenyimpananData.getDataPeminjaman().get(inputInt).getTanggalPinjam()+"" +
                    "\n      5. Tanggal Kembali: "+PenyimpananData.getDataPeminjaman().get(inputInt).getTanggalKembali()+"" +
                    "\n      6. Deadline Peminjaman: "+PenyimpananData.getDataPeminjaman().get(inputInt).getDeadlinePeminjaman()+"" +
                    "\n      7. Status Keterlambatan: "+PenyimpananData.getDataPeminjaman().get(inputInt).getStatusKeterlambatanPengembalian()+"");

            System.out.print("\nIngin melihat detail peminjaman lain juga? (y/n): "); inputStr = input.nextLine();

            switch (inputStr.toLowerCase()){
                case "y": continue loop;
                case "n": break loop;
            }
        }
    }

    //============================================================================================================================================================================================================
}
