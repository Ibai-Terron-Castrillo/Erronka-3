$(document).ready(function () {
  let currentPage = 1
  $("#search-button").on("click", function (e) {
    e.preventDefault();
    currentPage = 1;
    $('#movies').empty();
    loadMovies(currentPage);
});
  function loadMovies (page) {
    var search = $("#search").val();
    var generoa = $("#genero-search").val();
    var sailkapena = $("#sailkapena-search").val();


    $.ajax({
      url: 'KargatuPelikulak.php?page=1',
      type: 'GET',
      data: { page: page, search: search, generoa: generoa, sailkapena: sailkapena },
      dataType: 'json',
      success: function (data) {
        if (data.length > 0) {
          data.forEach(function (movies) {
            $('#movies').append(`
                                <div class="col-md-4 mb-5">
                                  <div class="card h-100">
                                      <div class="card-body">
                                          <h2 class="card-title">${movies.izena}</h2>
                                          <img src="${movies.kartela}" alt="" style="width:200px; height: 300px;">
                                      </div>
                                      <div class="card-footer"><a class="btn btn-primary btn-sm" href="pelikula.php?id=${movies.id}">Informazio Gehiago</a></div>
                                  </div>
                              </div>
                            `)
          })
        } else {
          $('#load-more').hide()
        }
      },
      error: function () {
        alert('Ezin izan dira kargatu pelikulak.')
      }
    })
  }

  loadMovies(currentPage)

  $('#load-more').click(function () {
    currentPage++
    loadMovies(currentPage)
  })
})
