<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"
	integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>BookYourShow</title>
</head>
<body>


	<nav class="navbar navbar-expand-lg text-bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="/index">BookYourShow</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mb-2 mb-lg-0 nav-pills">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/index">Home</a></li>
					<li class="nav-item"><div class="invisible">...</div></li>

					<li class="nav-item">

						<form action="/search">
							<div class="input-group">
								<input type="search" class="form-control rounded"
									placeholder="Search" aria-label="Search"
									aria-describedby="search-addon" name="city" />
								<button type="button" class="btn btn-outline-primary">search</button>
							</div>
						</form>

					</li>

					<li class="nav-item dropdown ml-auto"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false">${user}</a>
						<ul class="dropdown-menu dropdown-menu-end">
							<li><a class="dropdown-item" href="/editUser">Edit user</a></li>
							<li><a class="dropdown-item" href="/myBookings">My Bookings</a></li>

							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="/logout">Logout</a></li>
						</ul></li>

				</ul>
			</div>
		</div>
	</nav>


	<br>
	<h2>${movieName}</h2>

	<div class="d-flex flex-row align-items-center mb-4">
		<i class="fas fa-user fa-lg me-3 fa-fw"></i>
		<c:set var="error" scope="session" value="${quantityError}" />
		<c:if test="${error != null}">
			<div class=" form-outline flex-fill mb-0 alert alert-danger "
				role="alert">${quantityError}</div>
		</c:if>
		<c:if test="${error == null }">
			<br>
			<br>
		</c:if>
	</div>


	<table class="table" style="width: 100">
		<thead class="table-dark">
			<tr>
				<th class="w-12" scope="col">Theatre Name</th>
				<th class="w-12" scope="col">City</th>
				<th class="w-12" scope="col">Quantity</th>
				<th class="w-12" scope="col">Date</th>
				<th class="w-12" scope="col">Price</th>
				<th class="w-12" scope="col">Action</th>

			</tr>
		</thead>

		<tbody>
			<c:forEach items="${movieDetails}" var="movie">
				<tr>
					<td>${movie.theatreName}</td>
					<td>${movie.city}</td>
					<td>${movie.quantity}</td>
					<td>${movie.date}</td>
					<td>${movie.price}</td>


					<td>
						<!-- Button trigger modal -->
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target="#exampleModalCenter">Select quantity</button> <!-- Modal -->
						<div class="modal fade" id="exampleModalCenter" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalCenterTitle"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLongTitle">Please
											select quantity</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<form action="/confirm/${movie.movieId}/${movie.theatreName}">
										<div class="modal-body">
											<input type="number" name="quantity" />
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">cancel</button>
											<button type="submit" class="btn btn-success">Confirm
												booking</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</td>



				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>

<script src="https://code.jquery.com/jquery-3.1.1.min.js">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js">
	
</script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js">
	
</script>
<!--JS below-->


<!--modal-->
<script>
	$(document).ready(function() {
		$("#myModal").modal();
	});
</script>

</html>