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
			<a class="navbar-brand" href="#">BookYourShow</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mb-2 mb-lg-0 nav-pills">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="/index">Home</a></li>
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

	<br>
	<h2>List of Movies Available to add</h2>
	<br><br>
	<table class="table" style="width: 100">
		<thead class="table-dark">
			<tr>
				<th class="w-12" scope="col">Id</th>
				<th class="w-12" scope="col">Movie Name</th>
				<th class="w-12" scope="col">Genre</th>
				<th class="w-12" scope="col">Language</th>
				<th class="w-12" scope="col">Location</th>
				<th class="w-12" scope="col">Action</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${movies}" var="movie">
				<tr>
					<th scope="row">${movie.movieId}</th>
					<td>${movie.movieName}</td>
					<td>${movie.genres}</td>
					<td>${movie.language}</td>
					<td>${movie.city}</td>
					<td><a href="/addToTheatre/${movie.movieId }"><button class="btn btn-success">Add</button></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>