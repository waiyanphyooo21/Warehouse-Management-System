<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Ngwe Pone Gyi - Inventory Management System</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
<%--  JavaScript--%>
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

  <style>
    html, body {
      height: 100%;
      margin: 0;
      overflow: hidden;
      font-family: 'Inter', sans-serif;
      background-color: #f8f9fa;
      color: #212529;
    }

    .custom-navbar {
      background-color: #FF4D00;
    }

    .navbar {
      position: fixed;
      top: 0;
      left: 0;
      right: 0;
      z-index: 1040;
    }

    .sidebar {
      background-color: white;
      position: fixed;
      top: 72px; /* height of navbar */
      left: 0;
      width: 250px;
      height: calc(100vh - 72px);
      box-shadow: 2px 0 10px rgba(0, 0, 0, 0.05);
      padding: 20px 0;
      z-index: 1030;
      overflow-y: auto;
    }

    .main-content {
      margin-left: 250px;
      margin-top: 72px; /* height of navbar */
      padding: 30px;
      height: calc(100vh - 72px);
      overflow-y: auto;
    }

    .nav-menu > .nav-item {
      padding: 0;
    }

    .nav-item {
      padding: 0;
    }

    .nav-menu > .nav-item > .nav-link {
      display: block;
      padding: 10px 20px;
      color: #212529;
      text-decoration: none;
      transition: background-color 0.3s ease;
    }

    .nav-menu > .nav-item > .nav-link:hover,
    .nav-menu > .nav-item > .nav-link.active {
      background-color: #f0f4ff;
      color: #FF4D00;
    }

    .sidebar .collapse .nav-link {
      display: block;
      padding: 8px 20px;
      border-radius: 0.375rem;
      color: #212529;
      text-decoration: none;
      transition: background-color 0.3s ease;
    }

    .sidebar .collapse .nav-link:hover {
      background-color: #e7eaff;
      color: #212529;
    }

    .sidebar .collapse .nav-link.active {
      background-color: #d0d8ff;
      font-weight: 600;
      color: #212529;
    }

    .nav-link.text-danger {
      color: #dc3545 !important;
    }

    .top-bar {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .search-container {
      position: relative;
      flex-grow: 1;
      margin-right: 15px;
    }

    .search-container input {
      padding-left: 35px;
    }

    .search-container i {
      position: absolute;
      top: 50%;
      left: 10px;
      transform: translateY(-50%);
      color: gray;
    }

    .icon-group i {
      margin-left: 15px;
      cursor: pointer;
      color: #333;
    }

    .card {
      border: none;
      border-radius: 10px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.05);
    }

    .card-header {
      background-color: white;
      padding: 15px 20px;
      font-weight: 600;
      display: flex;
      justify-content: flex-end;
    }

    .dashboard-card h5,
    .dashboard-card h3 {
      margin: 0;
    }

    .dashboard-card i {
      min-width: 40px;
      text-align: right;
      color: #6c757d;
    }
    /*Logout*/
    #logoutConfirmModal .modal-header {
      border-bottom: none;
    }

    #logoutConfirmModal .modal-footer {
      border-top: none;
    }

    /* Responsive Sidebar */
    @media (max-width: 991.98px) {
      .sidebar {
        display: none;
      }

      .main-content {
        margin-left: 0;
        padding: 20px;
        margin-top: 72px;
      }
    }

    @media (min-width: 992px) {
      .offcanvas-start {
        display: none !important;
      }
    }
  </style>


</head>
<body>
<!-- Logout Confirmation Modal -->
<div class="modal fade" id="logoutConfirmModal" tabindex="-1" aria-labelledby="logoutConfirmLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="logoutConfirmLabel">Confirm Logout</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Are you sure you want to logout?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">Cancel</button>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-sm">Logout</a>
      </div>
    </div>
  </div>
</div>

<!-- Top Navbar -->
<%--Image and Text--%>
<%--<nav class="navbar navbar-expand-lg custom-navbar">--%>
<%--    <div class="container-fluid">--%>
<%--        <button class="btn text-white d-lg-none me-2" type="button" data-bs-toggle="offcanvas" data-bs-target="#mobileSidebar">--%>
<%--            <i class="fas fa-bars fa-lg"></i>--%>
<%--        </button>--%>
<%--        <a class="navbar-brand" href="${pageContext.request.contextPath}/">--%>
<%--            <img src="${pageContext.request.contextPath}/resources/images/npg.png" alt="npg" width="60" height="60" style="margin-left: 50px;" />--%>
<%--        </a>--%>
<%--        <div class="mx-auto text-white fw-bold fs-4">Ngwe Pone Gyi</div>--%>
<%--    </div>--%>
<%--</nav>--%>
<nav class="navbar navbar-expand-lg custom-navbar">
  <div class="container-fluid position-relative">

    <!-- Hamburger menu button on the left -->
    <button class="btn text-white d-lg-none position-absolute start-0" type="button" data-bs-toggle="offcanvas" data-bs-target="#mobileSidebar" aria-label="Toggle navigation">
      <i class="fas fa-bars fa-lg"></i>
    </button>

    <!-- Centered brand logo -->
    <a class="navbar-brand mx-auto" href="${pageContext.request.contextPath}/">
      <img src="${pageContext.request.contextPath}/resources/images/npg.png" alt="npg" width="60" height="60" />
    </a>

  </div>
</nav>

<!-- Sidebar for Desktop -->
<div class="sidebar d-none d-lg-block">
  <div class="nav-menu">
    <div class="nav-item ${active == 'dashboard' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/dashboard" class="nav-link ${active == 'dashboard' ? 'active' : ''}">
        <i class="fas fa-tachometer-alt me-2"></i>Dashboard
      </a>
    </div>
    <div class="nav-item ${param.active == 'product-add' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/products" class="nav-link">
        <i class="fas fa-box me-2"></i>Products
      </a>
    </div>
    <div class="nav-item ${param.active == 'customer' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/customers" class="nav-link">
        <i class="fas fa-users me-2"></i>Customer Info
      </a>
    </div>
    <div class="nav-item ${param.active == 'supplier' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/supplier/list" class="nav-link">
        <i class="fas fa-truck me-2"></i>Supplier Info
      </a>
    </div>
    <div class="nav-item ${param.active == 'warehouse' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/warehouse/list" class="nav-link">
        <i class="fas fa-warehouse me-2"></i>Warehouse
      </a>
    </div>
    <%--  Sub Stock Menu   --%>
    <div class="nav-item">
      <a class="nav-link d-flex justify-content-between align-items-center" data-bs-toggle="collapse" href="#stockMenu" role="button" aria-expanded="false" aria-controls="stockMenu">
        <span><i class="fas fa-boxes me-2"></i>Stock</span>
        <i class="fas fa-chevron-down small"></i>
      </a>

      <div class="collapse ps-4
    ${param.active == 'stock-in' || param.active == 'stock-out' || param.active == 'stock-balance' ? 'show' : ''}"
           id="stockMenu">
        <a href="${pageContext.request.contextPath}/stock/in-list"
           class="nav-link d-block py-2 px-3 rounded ${param.active == 'stock-in' ? 'active' : ''}">
          <i class="fas fa-arrow-down me-2"></i> Stock In
        </a>
        <a href="${pageContext.request.contextPath}/stock/out-list"
           class="nav-link d-block py-2 px-3 rounded ${param.active == 'stock-out' ? 'active' : ''}">
          <i class="fas fa-arrow-up me-2"></i> Stock Out
        </a>
        <a href="${pageContext.request.contextPath}/stock/balance-list"
           class="nav-link d-block py-2 px-3 rounded ${param.active == 'stock-balance' ? 'active' : ''}">
          <i class="fas fa-boxes me-2"></i> Stock Balance
        </a>
      </div>

    </div>
    <div class="nav-item ${param.active == 'voucher' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/voucher/list" class="nav-link">
        <i class="fas fa-ticket-alt me-2"></i>Vouchers
      </a>
    </div>
  </div>
</div>

<!-- Offcanvas Sidebar for Mobile -->
<div class="offcanvas offcanvas-start" tabindex="-1" id="mobileSidebar">
  <div class="offcanvas-header">
    <h5 class="offcanvas-title">Menu</h5>
    <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
  </div>
  <div class="offcanvas-body">
    <div class="nav-menu">
      <%-- Same sidebar links duplicated for mobile --%>
      <%-- You can move this to a fragment if you want to avoid duplication --%>
      <div class="nav-item ${param.active == 'dashboard' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/" class="nav-link ${param.active == 'dashboard' ? 'text-danger' : ''}">
          <i class="fas fa-tachometer-alt me-2"></i>Dashboard
        </a>
      </div>
        <div class="nav-item ${param.active == 'product-add' ? 'active' : ''}">
          <a href="${pageContext.request.contextPath}/products" class="nav-link">
            <i class="fas fa-box me-2"></i>Products
          </a>
        </div>
        <div class="nav-item ${param.active == 'customer' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/customer/list" class="nav-link">
          <i class="fas fa-users me-2"></i>Customer Info
        </a>
      </div>
      <div class="nav-item ${param.active == 'supplier' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/supplier/list" class="nav-link">
          <i class="fas fa-truck me-2"></i>Supplier Info
        </a>
      </div>
      <div class="nav-item ${param.active == 'warehouse' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/warehouse/list" class="nav-link">
          <i class="fas fa-warehouse me-2"></i>Warehouse
        </a>
      </div>

      <%--      Sub Stock Menu          --%>
      <div class="nav-item">
        <a class="nav-link d-flex justify-content-between align-items-center" data-bs-toggle="collapse" href="#stockMenu" role="button" aria-expanded="false" aria-controls="stockMenu">
          <span><i class="fas fa-boxes me-2"></i>Stock</span>
          <i class="fas fa-chevron-down small"></i>
        </a>

        <div class="collapse ps-4
    ${param.active == 'stock-in' || param.active == 'stock-out' || param.active == 'stock-balance' ? 'show' : ''}"
             id="stockMenu">
          <a href="${pageContext.request.contextPath}/stock/in-list"
             class="nav-link d-block py-2 px-3 rounded ${param.active == 'stock-in' ? 'active' : ''}">
            <i class="fas fa-arrow-down me-2"></i> Stock In
          </a>
          <a href="${pageContext.request.contextPath}/stock/out-list"
             class="nav-link d-block py-2 px-3 rounded ${param.active == 'stock-out' ? 'active' : ''}">
            <i class="fas fa-arrow-up me-2"></i> Stock Out
          </a>
          <a href="${pageContext.request.contextPath}/stock/balance-list"
             class="nav-link d-block py-2 px-3 rounded ${param.active == 'stock-balance' ? 'active' : ''}">
            <i class="fas fa-boxes me-2"></i> Stock Balance
          </a>
        </div>

      </div>
      <div class="nav-item ${param.active == 'voucher' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/voucher" class="nav-link">
          <i class="fas fa-ticket-alt me-2"></i>Vouchers
        </a>
      </div>
    </div>
  </div>
</div>

<!-- Main Content -->
<div class="main-content">
  <div class="top-bar mb-4">
    <div class="search-container position-relative d-flex align-items-center">
      <i class="fas fa-search position-absolute search-icon ms-3"></i>
      <input type="search" class="form-control ps-5" placeholder="Search..." />

      <div class="position-absolute end-0 pe-3 d-flex align-items-center gap-3">
        <i class="fas fa-bell fa-lg" style="cursor: pointer;"></i>

        <div class="dropdown">
          <a href="#" id="userDropdown" data-bs-toggle="dropdown" aria-expanded="false" class="text-dark">
            <i class="fas fa-user-circle fa-lg" style="cursor: pointer;"></i>
          </a>
          <ul class="dropdown-menu dropdown-menu-end shadow" aria-labelledby="userDropdown">
<%--            <li class="dropdown-header">${user.name}</li>--%>
            <c:if test="${not empty sessionScope.loggedInUser}">
              <li class="dropdown-header">${sessionScope.loggedInUser.fullName}</li>
            </c:if>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/edit">Profile</a></li>
<%--            <li><hr class="dropdown-divider" /></li>--%>
<%--            <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/logout">Logout</a></li>--%>
            <li>
                <a href="#"
                    class="dropdown-item text-danger"
                    data-bs-toggle="modal"
                    data-bs-target="#logoutConfirmModal"
                >
                    Logout
                </a>
            </li>
          </ul>
        </div>
      </div>

    </div>
  </div>

  <h4 class="mb-4">Dashboard Overview</h4>
  <div class="row mb-4 g-4">
    <!-- Total Stock -->
    <div class="col-md-3">
      <div class="card dashboard-card text-center p-3">
        <div class="fs-3 mb-2 text-primary">
          <i class="fas fa-boxes"></i>
        </div>
        <div class="fw-semibold fs-6 mb-1">Total Stock</div>
        <div class="fs-5">1000</div>
      </div>
    </div>

    <!-- Total Sales -->
    <div class="col-md-3">
      <div class="card dashboard-card text-center p-3">
        <div class="fs-3 mb-2 text-success">
          <i class="fas fa-money-bill-wave"></i>
        </div>
        <div class="fw-semibold fs-6 mb-1">Total Sales</div>
        <div class="fs-5">MMK 3,000,000</div>
      </div>
    </div>

    <!-- Low Stock -->
    <div class="col-md-3">
      <div class="card dashboard-card text-center p-3">
        <div class="fs-3 mb-2 text-warning">
          <i class="fas fa-exclamation-triangle"></i>
        </div>
        <div class="fw-semibold fs-6 mb-1">Low Stock</div>
        <div class="fs-5">12</div>
      </div>
    </div>

    <!-- Out of Stock -->
    <div class="col-md-3">
      <div class="card dashboard-card text-center p-3">
        <div class="fs-3 mb-2 text-danger">
          <i class="fas fa-times-circle"></i>
        </div>
        <div class="fw-semibold fs-6 mb-1">Out of Stock</div>
        <div class="fs-5">4</div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="card mb-4">
        <div class="card-body">
          <h5 class="card-title">Recent Customers</h5>
          <div class="table-responsive">
            <table class="table table-hover">
              <thead>
              <tr>
                <th>No</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Action</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${recentCustomers}" var="customer" varStatus="status" end="4">
                <tr>
                  <td>${status.index + 1}</td>  <!-- Serial No -->
                  <td>${customer.customerName}</td>
                  <td>${customer.customerPh}</td>
                  <td>
                    <a href="${pageContext.request.contextPath}/customers/edit/${customer.customerId}" class="btn btn-sm btn-outline-primary">
                      <i class="fas fa-edit"></i>
                    </a>
                  </td>
                </tr>
              </c:forEach>
              </tbody>

            </table>
          </div>
          <a href="${pageContext.request.contextPath}/customers" class="btn btn-sm btn-outline-primary">View All Customers</a>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <div class="card mb-4">
        <div class="card-body">
          <h5 class="card-title">Recent Stock In</h5>
          <div class="table-responsive">
            <table class="table table-hover">
              <thead>
              <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Product</th>
                <th>Qty</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${recentStockIns}" var="stockIn" end="4">
                <tr>
                  <td>IN${stockIn.id}</td>
                  <td>${stockIn.date}</td>
                  <td>${stockIn.productName}</td>
                  <td>${stockIn.quantity}</td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
          <a href="${pageContext.request.contextPath}/stock/in-list" class="btn btn-sm btn-outline-primary">View All Stock In</a>
        </div>
      </div>
    </div>
  </div>
  <div class="container mt-4">
    <h4>Stock Overview</h4>
    <div style="max-width: 400px; margin: auto;">
      <canvas id="stockChart"></canvas>
    </div>
  </div>


</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/js/all.min.js"></script>

<script>
  const ctx = document.getElementById('stockChart').getContext('2d');
  const stockChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: ['Customers', 'Suppliers', 'Products', 'Total Stock'],
      datasets: [{
        label: 'Overview Count',
        data: [
          ${customerCount},
          ${supplierCount},
          ${productCount},
          ${totalStock}
        ],
        backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0'],
        borderColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0'],
        borderWidth: 1
      }]
    },
    options: {
      responsive: true,
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
</script>

</body>
</html>
