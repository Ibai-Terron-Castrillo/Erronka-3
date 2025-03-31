using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Threading.Tasks;

namespace MauiApp1
{
    public static class DatabaseHelper
    {
        private static string connectionString = "Server=172.16.237.169;Port=3307;Database=erronka3;User=el-umbral;Password=6Taldea6;";

        public static async Task<(bool isValid, string message, int userId)> VerifyLoginAsync(string email, string password)
        {
            try
            {
                using (var connection = new MySqlConnection(connectionString))
                {
                    await connection.OpenAsync();

                    string query = "SELECT id, izena FROM bezeroa WHERE email = @Email AND pasahitza = @Password";

                    using (var cmd = new MySqlCommand(query, connection))
                    {
                        cmd.Parameters.AddWithValue("@Email", email);
                        cmd.Parameters.AddWithValue("@Password", password);

                        using (var reader = await cmd.ExecuteReaderAsync())
                        {
                            if (await reader.ReadAsync())
                            {
                                int userId = reader.GetInt32("id");
                                string izena = reader.GetString("izena");
                                return (true, $"Ongi etorri, {izena}", userId);
                            }
                            else
                            {
                                return (false, "Datu Okerrak", 0);
                            }
                        }
                    }
                }
            }
            catch (MySqlException ex)
            {
                switch (ex.Number)
                {
                    case 0:
                        return (false, "Cannot connect to server. Contact administrator", 0);
                    case 1045:
                        return (false, "Invalid username/password for database", 0);
                    default:
                        return (false, $"Database error: {ex.Message}", 0);
                }
            }
            catch (Exception ex)
            {
                return (false, $"Unexpected error: {ex.Message}", 0);
            }
        }

        public static async Task<List<Booking>> GetUserBookingsAsync(int userId)
        {
            var bookings = new List<Booking>();

            try
            {
                using (var connection = new MySqlConnection(connectionString))
                {
                    await connection.OpenAsync();

                    string query = @"SELECT e.id, o.eguna, o.ordua, a.izena, e.kopurua 
                                    FROM erreserba e 
                                    JOIN ordutegia o ON e.id_ordutegi = o.id 
                                    JOIN aretoa a ON o.id_areto = a.id 
                                    WHERE e.egoera = 'Ordaindua' AND e.id_bezero = @UserId";

                    using (var cmd = new MySqlCommand(query, connection))
                    {
                        cmd.Parameters.AddWithValue("@UserId", userId);

                        using (var reader = await cmd.ExecuteReaderAsync())
                        {
                            while (await reader.ReadAsync())
                            {
                                var booking = new Booking
                                {
                                    id = reader.IsDBNull("id") ? 0 : reader.GetInt32("id"),

                                    // Manejo seguro de fechas
                                    Eguna = reader.IsDBNull("eguna") ? DateTime.MinValue : reader.GetDateTime("eguna"),

                                    // Conversión segura para la hora (TIME de MySQL puede venir como TimeSpan)
                                    Ordua = reader["ordua"] is TimeSpan time ? time.ToString(@"hh\:mm") : reader["ordua"].ToString(),

                                    AretoaIzena = reader.IsDBNull("izena") ? string.Empty : reader.GetString("izena"),
                                    Kopurua = reader.IsDBNull("kopurua") ? 0 : reader.GetInt32("kopurua")
                                };

                                bookings.Add(booking);
                            }
                        }
                    }
                }
            }
            catch (MySqlException ex)
            {
                switch (ex.Number)
                {
                    case 0:
                        throw new Exception("Cannot connect to server. Contact administrator");
                    case 1045:
                        throw new Exception("Invalid username/password for database");
                    default:
                        throw new Exception($"Database error: {ex.Message}");
                }
            }
            catch (Exception ex)
            {
                throw new Exception($"Unexpected error: {ex.Message}");
            }

            return bookings;
        }
    
    public static async Task<bool> DeleteBookingAsync(int bookingId)
        {
            try
            {
                using (var connection = new MySqlConnection(connectionString))
                {
                    await connection.OpenAsync();

                    string query = "DELETE FROM erreserba WHERE id = @BookingId";

                    using (var cmd = new MySqlCommand(query, connection))
                    {
                        cmd.Parameters.AddWithValue("@BookingId", bookingId);

                        int rowsAffected = await cmd.ExecuteNonQueryAsync();
                        return rowsAffected > 0;
                    }
                }
            }
            catch (MySqlException ex)
            {
                // Manejo de errores específicos de MySQL
                switch (ex.Number)
                {
                    case 0:
                        throw new Exception("Cannot connect to server. Contact administrator");
                    case 1045:
                        throw new Exception("Invalid username/password for database");
                    default:
                        throw new Exception($"Database error: {ex.Message}");
                }
            }
            catch (Exception ex)
            {
                throw new Exception($"Unexpected error: {ex.Message}");
            }
        }
    }
}
    public class Booking
    {
        public int id { get; set; }
        public DateTime Eguna { get; set; }
        public string Ordua { get; set; }
        public string AretoaIzena { get; set; }
        public int Kopurua { get; set; }
    }
