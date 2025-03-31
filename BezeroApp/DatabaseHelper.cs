using MySql.Data.MySqlClient;
using System;
using System.Threading.Tasks;

namespace MauiApp1
{
    public static class DatabaseHelper
    {
        // Use your actual server IP, not localhost
        private static string connectionString = "Server=192.168.1.39:3306;Database=erronka3;User=PRUEBINI;Password=1MG2024;Port=3306;SslMode=None;Connect Timeout=30;";

        public static async Task<(bool isValid, string message)> VerifyLoginAsync(string email, string password)
        {
            try
            {
                using (var connection = new MySqlConnection(connectionString))
                {
                    await connection.OpenAsync();

                    // Important: Use parameterized queries to prevent SQL injection
                    string query = "SELECT COUNT(*) FROM bezeroa WHERE email = @Email AND pasahitza = @Password";

                    using (var cmd = new MySqlCommand(query, connection))
                    {
                        cmd.Parameters.AddWithValue("@Email", email);
                        cmd.Parameters.AddWithValue("@Password", password);

                        var result = await cmd.ExecuteScalarAsync();
                        int count = Convert.ToInt32(result);

                        return (count > 0, count > 0 ? "Login successful" : "Invalid credentials");
                    }
                }
            }
            catch (MySqlException ex)
            {
                // More specific error handling
                switch (ex.Number)
                {
                    case 0:
                        return (false, "Cannot connect to server. Contact administrator");
                    case 1045:
                        return (false, "Invalid username/password for database");
                    default:
                        return (false, $"Database error: {ex.Message}");
                }
            }
            catch (Exception ex)
            {
                return (false, $"Unexpected error: {ex.Message}");
            }
        }
    }
}