using MySql.Data.MySqlClient;
using System;
using System.Threading.Tasks;

namespace MauiApp1
{
    public class DatabaseHelper
    {
        private static string connectionString = "Server=localhost;Database=erronka3;User=root;Password=1MG2024;";

        // Método para verificar el login
        public static async Task<bool> VerifyLoginAsync(string email, string password)
        {
            bool isValid = false;

            try
            {
                using (var connection = new MySqlConnection(connectionString))
                {
                    await connection.OpenAsync();

                    string query = "SELECT COUNT(*) FROM users WHERE email = @Email AND pasahitza = @Password";

                    using (var cmd = new MySqlCommand(query, connection))
                    {
                        cmd.Parameters.AddWithValue("@Email", email);
                        cmd.Parameters.AddWithValue("@Password", password);  // ¡No uses esto en producción! (ver más abajo)

                        var result = await cmd.ExecuteScalarAsync();
                        isValid = Convert.ToInt32(result) > 0;
                    }
                }
            }
            catch (Exception ex)
            {
                // Manejo de errores
                Console.WriteLine($"Error: {ex.Message}");
            }

            return isValid;
        }
    }
}
