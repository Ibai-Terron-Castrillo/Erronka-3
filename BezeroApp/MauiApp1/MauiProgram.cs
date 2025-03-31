using Microsoft.Extensions.Logging;
using MySql.Data.MySqlClient;

namespace MauiApp1
{
    public static class MauiProgram
    {
        public static MauiApp CreateMauiApp()
        {
            var builder = MauiApp.CreateBuilder();
            builder
                .UseMauiApp<App>()
                .ConfigureFonts(fonts =>
                {
                    fonts.AddFont("OpenSans-Regular.ttf", "OpenSansRegular");
                    fonts.AddFont("OpenSans-Semibold.ttf", "OpenSansSemibold");
                });

            // Register your services here
            builder.Services.AddTransient<MySqlConnection>(_ =>
                new MySqlConnection("Server=192.168.1.39:3306;Database=erronka3;User=PRUEBINI;Password=1MG2024;"));

#if DEBUG
            builder.Logging.AddDebug();
#endif

            return builder.Build();
        }
        private static void ConfigureServices(IServiceCollection services)
        {
            // Register other services here
            services.AddTransient<HomePage>();
        }
    }
}