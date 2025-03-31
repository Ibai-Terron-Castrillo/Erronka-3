using Microsoft.Maui.Controls;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace MauiApp1
{
    public partial class HomePage : ContentPage
    {
        public HomePage()
        {
            BackgroundColor = Colors.Black;
        }

        public void Initialize(int userId)
        {
            var stackLayout = new StackLayout
            {
                Padding = new Thickness(20),
                Spacing = 15
            };

            // Agregar un texto de ejemplo
            var exampleLabel = new Label
            {
                Text = "Zure Erreserbak",
                TextColor = Colors.White,
                FontSize = 24,
                HorizontalOptions = LayoutOptions.Center
            };
            stackLayout.Children.Add(exampleLabel);

            LoadBookings(userId, stackLayout);
            Content = new ScrollView { Content = stackLayout };
        }

        private async void LoadBookings(int userId, StackLayout stackLayout)
        {
            try
            {
                var bookings = await DatabaseHelper.GetUserBookingsAsync(userId);

                if (bookings == null || bookings.Count == 0)
                {
                    var noBookingsLabel = new Label
                    {
                        Text = "Ez dago erreserbarik.",
                        TextColor = Colors.White,
                        FontSize = 18,
                        HorizontalOptions = LayoutOptions.Center
                    };
                    stackLayout.Children.Add(noBookingsLabel);
                    return;
                }

                foreach (var booking in bookings)
                {
                    var bookingFrame = new Frame
                    {
                        BorderColor = Colors.White,
                        CornerRadius = 10,
                        Padding = new Thickness(10),
                        Margin = new Thickness(0, 0, 0, 10),
                        BackgroundColor = Colors.DarkGray
                    };

                    var bookingLabel = new Label
                    {
                        Text = $"Eguna: {booking.Eguna.ToShortDateString()}\nOrdua: {booking.Ordua}\nAretoa: {booking.AretoaIzena}\nKopurua: {booking.Kopurua}",
                        TextColor = Colors.White,
                        FontSize = 18
                    };

                    var cancelButton = new Button
                    {
                        Text = "Erreserba ezeztatu",
                        BackgroundColor = Colors.Red,
                        TextColor = Colors.White,
                        Margin = new Thickness(0, 10, 0, 0)
                    };
                    cancelButton.Clicked += async (sender, e) => await CancelBooking(booking.id, stackLayout, bookingFrame);

                    var downloadButton = new Button
                    {
                        Text = "Faktura Deskargatu",
                        BackgroundColor = Colors.Blue,
                        TextColor = Colors.White,
                        Margin = new Thickness(0, 10, 0, 0)
                    };
                    downloadButton.Clicked += (sender, e) => DownloadInvoice(booking.id);

                    var bookingStack = new StackLayout();
                    bookingStack.Children.Add(bookingLabel);
                    bookingStack.Children.Add(cancelButton);
                    bookingStack.Children.Add(downloadButton);

                    bookingFrame.Content = bookingStack;
                    stackLayout.Children.Add(bookingFrame);
                }
            }
            catch (Exception ex)
            {
                var errorLabel = new Label
                {
                    Text = $"Errorea erreserbak kargatzean: {ex.Message}",
                    TextColor = Colors.Red,
                    FontSize = 18,
                    HorizontalOptions = LayoutOptions.Center
                };
                stackLayout.Children.Add(errorLabel);
            }
        }

        private async Task CancelBooking(int bookingId, StackLayout stackLayout, Frame bookingFrame)
        {
            try
            {
                bool isDeleted = await DatabaseHelper.DeleteBookingAsync(bookingId);
                if (isDeleted)
                {
                    stackLayout.Children.Remove(bookingFrame);
                }
                else
                {
                    await DisplayAlert("Errorea", "Erreserba ezin izan da ezeztatu.", "Onartu");
                }
            }
            catch (Exception ex)
            {
                await DisplayAlert("Errorea", $"Errorea erreserba ezeztatzen: {ex.Message}", "Onartu");
            }
        }

        private void DownloadInvoice(int bookingId)
        {
            var url = $"http://172.16.237.169/uploads/Factura_{bookingId}.pdf";
            Launcher.OpenAsync(new Uri(url));
        }
    }
}
