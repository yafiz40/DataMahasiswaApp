import dao.Dao
import dao.StudentDao
import model.Student
import kotlin.system.exitProcess

class App {
    private val dao: Dao<Student, String> = StudentDao()

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            App().startApp()
        }
    }

    fun startApp() {
        navigateToMainMenu()
    }

    fun printHeader() {
        println("""
            =================================
            Aplikasi Data Mahasiswa
            =================================
            1. Cetak Data Mahasiswa
            2. Tambah Data Mahasiswa
            3. Hapus Data Mahasiswa
            4. Keluar
            =================================
            Masukkan Pilihan Anda (1,2,3,4) ?
            =================================
        """.trimIndent())
    }

    fun navigateToMenu(menu: String) {
        when(menu) {
            "1" -> {
                openMenuPrintStudent()
            }
            "2" -> {
                openMenuInsertStudent()
            }
            "3" -> {
                openMenuDeleteStudent()
            }
            "4" -> {
                exitProcess(0)
            }
            else -> {
                println("Pilihan tidak ada")
            }
        }
        askToMainMenu()
    }

    private fun openMenuDeleteStudent() {
        println("=================================")
        println("Hapus data dengan NIM = ")
        readLine()?.let {
            dao.deleteData(it)
        }
        println("=================================")
        println("Hapus Data Berhasil")
    }

    private fun openMenuInsertStudent() {
        println("=================================")
        println("Nama Mahasiswa = ")
        val name = readLine().orEmpty()
        println("NIM Mahasiswa = ")
        val nim = readLine().orEmpty()
        println("Jurusan Mahasiswa = ")
        val major = readLine().orEmpty()
        println("Kelas = ")
        val className = readLine().orEmpty()
        println("Universitas = ")
        val university = readLine().orEmpty()
        dao.addData(Student(name, nim, major, className, university))
        println("=================================")
        println("Insert Data Berhasil")
    }

    private fun openMenuPrintStudent() {
        val students = dao.getData()
        if (students.isNotEmpty()) {
            students.forEachIndexed { index, student ->
                println("""
                =================================
                Mahasiswa ke-${index+1}
                =================================
                Nama        : ${student.name}
                NIM         : ${student.nim}
                Jurusan     : ${student.major}
                Kelas       : ${student.className}
                University  : ${student.university}
            """.trimIndent())
            }
        } else {
            println("""
                =================================
                Tidak Ada Data!
                =================================
            """.trimIndent())
        }
    }

    private fun askToMainMenu() {
        println("""
            =================================
            Kembali ke menu utama ? (Y/N)
            =================================
        """.trimIndent())
        if (readLine().equals("Y", ignoreCase = true)) {
            navigateToMainMenu()
        } else {
            exitProcess(0)
        }
    }

    private fun navigateToMainMenu() {
        printHeader()
        readLine()?.let {
            navigateToMenu(it)
        }
    }
}