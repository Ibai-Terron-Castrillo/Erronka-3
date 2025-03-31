using Microsoft.Maui.Controls;

namespace MauiApp1
{
    public partial class MainPage : ContentPage
    {
        private Entry emailEntry;
        private Entry passwordEntry;
        private Button loginButton;

        public MainPage()
        {
            InitializeComponent();

            // Remove the navigation bar
            NavigationPage.SetHasNavigationBar(this, false);

            // Set the background color of the page to black
            BackgroundColor = Colors.Black;

            emailEntry = new Entry
            {
                Placeholder = "Email",
                PlaceholderColor = Colors.Gray,
                TextColor = Colors.Black,
                BackgroundColor = Colors.LightGray
            };
            passwordEntry = new Entry
            {
                Placeholder = "Pasahitza",
                IsPassword = true,
                PlaceholderColor = Colors.Gray,
                TextColor = Colors.Black,
                BackgroundColor = Colors.LightGray
            };
            loginButton = new Button
            {
                Text = "Saioa Hasi",
                BackgroundColor = Color.FromArgb("#B22222"),
                TextColor = Colors.White
            };
            loginButton.Clicked += OnLoginClicked;

            Content = new StackLayout
            {
                Padding = new Thickness(20),
                Spacing = 15,
                Children = { emailEntry, passwordEntry, loginButton }
            };
        }

        private async void OnLoginClicked(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(emailEntry.Text) || string.IsNullOrWhiteSpace(passwordEntry.Text))
            {
                await DisplayAlert("Errorea", "Sartu datu guztiak", "Onartu");
                return;
            }

            loginButton.IsEnabled = false;
            loginButton.Text = "Autentikatzen...";

            try
            {
                var (isValid, message, userId) = await DatabaseHelper.VerifyLoginAsync(emailEntry.Text, passwordEntry.Text);

                if (isValid)
                {
                    await DisplayAlert("Ongi Etorri", message, "Onartu");

                    // Navegar a HomePage y pasar el userId
                    var homePage = new HomePage();
                    homePage.Initialize(userId);
                    await Navigation.PushAsync(homePage);
                }
                else
                {
                    await DisplayAlert("Errorea", message, "Onartu");
                }
            }
            finally
            {
                loginButton.IsEnabled = true;
                loginButton.Text = "Saioa Hasi";
            }
        }
    }
}
