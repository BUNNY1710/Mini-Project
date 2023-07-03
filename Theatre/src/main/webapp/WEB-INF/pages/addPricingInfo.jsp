<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css"
	rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js">
	
</script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js">
	
</script>
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
					<li class="nav-item"><a class="nav-link"
						aria-current="page" href="/index">Home</a></li>
					<li class="nav-item"><a class="nav-link active" href="/movies">Add
							Movie</a></li>
					<li class="nav-item dropdown ml-auto"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false">${theatre}</a>
						<ul class="dropdown-menu dropdown-menu-end">
							<li><a class="dropdown-item" href="/editTheatre">Edit Theatre</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="/logout">Logout</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="mask d-flex align-items-center h-100 gradient-custom-3">
		<div class="container h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-9 col-lg-7 col-xl-6">
					<div class="card" style="border-radius: 15px;">
						<div class="card-body p-5">
							<h2 class="text-uppercase text-center mb-5">Add ${movieName }
							</h2>

							<form:form action="/addMovie/${movieId}"
										method="post" modelAttribute="moviePricingDTO" >

								<div class="form-outline mb-4">
									<form:input type="number" path="price" id="form3Example1cg"
										class="form-control form-control-lg" /> <label
										class="form-label" for="form3Example1cg">Price</label>
								</div>

								<div class="form-outline mb-4">
									<form:input type="number" path="quantity" id="form3Example3cg"
										class="form-control form-control-lg" /> <label
										class="form-label" for="form3Example3cg">Quantity</label>
								</div>

								<div class="form-outline mb-4">
									<form:input type="date" path="date" pattern="yyyy-mm-dd"
										class="date form-control form-control form-control-lg"
										style="width: 200px" />

									<!-- <script type="text/javascript">
										$(".date").datepicker({
											format : "yyyy-mm-dd",
										});
									</script> -->
									<label class="form-label" for="form3Example3cg">Date</label>

								</div>


								<div class="d-flex justify-content-center">
									<form:button
										class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Add</form:button>
								</div>

							</form:form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>