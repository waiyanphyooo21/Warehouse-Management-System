<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Ngwe Pone Gyi - Supplier List</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />

  <style>
    /* Keep your existing styles exactly as in customer.jsp */
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
      top: 72px;
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
      margin-top: 72px;
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

    /*.nav-menu > .nav-item > .nav-link:hover,*/
    /*.nav-menu > .nav-item > .nav-link.active {*/
    /*    background-color: #f0f4ff;*/
    /*    color: #dc3545;*/
    /*    font-weight: 600;*/
    /*}*/
    .nav-menu > .nav-item > .nav-link:hover {
      background-color: #f0f4ff;
      color: #212529;
    }

    .nav-menu > .nav-item > .nav-link.active {
      background-color: #f0f4ff;
      color: #FF4D00;
      /*font-weight: 600;*/
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

    /* Logout Modal */
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

    /* Table styling for customer list */
    table.table thead.table-dark th {
      vertical-align: middle;
      text-align: center;
    }

    table.table tbody td {
      vertical-align: middle;
      text-align: center;
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
    <!-- unchanged navigation with active highlighting -->
    <div class="nav-item ${active == 'dashboard' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/dashboard" class="nav-link ${active == 'dashboard' ? 'active' : ''}">
        <i class="fas fa-tachometer-alt me-2"></i>Dashboard
      </a>
    </div>
    <div class="nav-item ${param.active == 'product-add' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/product/add" class="nav-link">
        <i class="fas fa-box me-2"></i>Products
      </a>
    </div>
    <div class="nav-item ${active == 'customer' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/customers" class="nav-link ${active == 'customer' ? 'active' : ''}">
        <i class="fas fa-users me-2"></i>Customer Info
      </a>
    </div>

    <div class="nav-item ${active == 'supplier' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/supplier/list" class="nav-link ${active == 'supplier' ? 'active' : ''}">
        <i class="fas fa-truck me-2"></i>Supplier Info
      </a>
    </div>
    <div class="nav-item ${active == 'warehouse' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/warehouse/list" class="nav-link ${active == 'warehouse' ? 'active' : ''}">
        <i class="fas fa-warehouse me-2"></i>Warehouse
      </a>
    </div>
    <!-- Stock submenu unchanged -->
    <div class="nav-item">
      <a class="nav-link d-flex justify-content-between align-items-center" data-bs-toggle="collapse" href="#stockMenu" role="button" aria-expanded="false" aria-controls="stockMenu">
        <span><i class="fas fa-boxes me-2"></i>Stock</span>
        <i class="fas fa-chevron-down small"></i>
      </a>
      <div class="collapse ps-4
      ${active == 'stock-in' || active == 'stock-out' || active == 'stock-balance' ? 'show' : ''}"
           id="stockMenu">
        <a href="${pageContext.request.contextPath}/stock/in-list"
           class="nav-link d-block py-2 px-3 rounded ${active == 'stock-in' ? 'active' : ''}">
          <i class="fas fa-arrow-down me-2"></i> Stock In
        </a>
        <a href="${pageContext.request.contextPath}/stock/out-list"
           class="nav-link d-block py-2 px-3 rounded ${active == 'stock-out' ? 'active' : ''}">
          <i class="fas fa-arrow-up me-2"></i> Stock Out
        </a>
        <a href="${pageContext.request.contextPath}/stock/balance-list"
           class="nav-link d-block py-2 px-3 rounded ${active == 'stock-balance' ? 'active' : ''}">
          <i class="fas fa-boxes me-2"></i> Stock Balance
        </a>
      </div>
    </div>
    <div class="nav-item ${active == 'voucher' ? 'active' : ''}">
      <a href="${pageContext.request.contextPath}/voucher/list" class="nav-link ${active == 'voucher' ? 'active' : ''}">
        <i class="fas fa-ticket-alt me-2"></i>Vouchers
      </a>
    </div>
  </div>
</div>

<!-- Offcanvas Sidebar for Mobile -->
<div class="offcanvas offcanvas-start" tabindex="-1" id="mobileSidebar">
  <div class="offcanvas-header">
    <h5 class="offcanvas-title">Menu</h5>
    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
  </div>
  <div class="offcanvas-body">
    <div class="nav-menu">
      <div class="nav-item ${active == 'dashboard' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/dashboard" class="nav-link ${active == 'dashboard' ? 'text-danger' : ''}">
          <i class="fas fa-tachometer-alt me-2"></i>Dashboard
        </a>
      </div>
      <div class="nav-item ${param.active == 'product-add' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/product/add" class="nav-link">
          <i class="fas fa-box me-2"></i>Products
        </a>
      </div>
      <div class="nav-item ${active == 'customer' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/customers" class="nav-link ${active == 'customer' ? 'text-danger' : ''}">
          <i class="fas fa-users me-2"></i>Customer Info
        </a>
      </div>
      <div class="nav-item ${active == 'supplier' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/supplier/list" class="nav-link ${active == 'supplier' ? 'text-danger' : ''}">
          <i class="fas fa-truck me-2"></i>Supplier Info
        </a>
      </div>
      <div class="nav-item ${active == 'warehouse' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/warehouse/list" class="nav-link ${active == 'warehouse' ? 'text-danger' : ''}">
          <i class="fas fa-warehouse me-2"></i>Warehouse
        </a>
      </div>
      <!-- Stock submenu unchanged -->
      <div class="nav-item">
        <a class="nav-link d-flex justify-content-between align-items-center" data-bs-toggle="collapse" href="#mobileStockMenu" role="button" aria-expanded="false" aria-controls="mobileStockMenu">
          <span><i class="fas fa-boxes me-2"></i>Stock</span>
          <i class="fas fa-chevron-down small"></i>
        </a>
        <div class="collapse ps-4
        ${active == 'stock-in' || active == 'stock-out' || active == 'stock-balance' ? 'show' : ''}"
             id="mobileStockMenu">
          <a href="${pageContext.request.contextPath}/stock/in-list"
             class="nav-link d-block py-2 px-3 rounded ${active == 'stock-in' ? 'active' : ''}">
            <i class="fas fa-arrow-down me-2"></i> Stock In
          </a>
          <a href="${pageContext.request.contextPath}/stock/out-list"
             class="nav-link d-block py-2 px-3 rounded ${active == 'stock-out' ? 'active' : ''}">
            <i class="fas fa-arrow-up me-2"></i> Stock Out
          </a>
          <a href="${pageContext.request.contextPath}/stock/balance-list"
             class="nav-link d-block py-2 px-3 rounded ${active == 'stock-balance' ? 'active' : ''}">
            <i class="fas fa-boxes me-2"></i> Stock Balance
          </a>
        </div>
      </div>
      <div class="nav-item ${active == 'voucher' ? 'active' : ''}">
        <a href="${pageContext.request.contextPath}/voucher/list" class="nav-link ${active == 'voucher' ? 'text-danger' : ''}">
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
            <c:if test="${not empty sessionScope.loggedInUser}">
              <li class="dropdown-header">${sessionScope.loggedInUser.fullName}</li>
            </c:if>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/edit">Profile</a></li>
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
  <h3 class="mb-4">Supplier List</h3>

  <div class="mb-3 text-end">
    <a href="${pageContext.request.contextPath}/supplier/add" class="btn btn-orange">
      <i class="fas fa-plus me-1"></i> Add Supplier
    </a>
  </div>

  <div class="table-responsive">
    <table class="table table-striped table-bordered align-middle">
      <thead class="table-dark">
      <tr>
        <th>No.</th>
        <th>Name</th>
        <th>Phone</th>
        <th>Address</th>
        <th style="width: 12%;">Actions</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="supplier" items="${suppliers}" varStatus="status">
        <tr>
          <td>${status.index + 1}</td>
          <td>${supplier.supplierName}</td>
          <td class="text-center">${supplier.supplierPh}</td>
          <td>${supplier.supplierAddress}</td>
          <td class="text-center">
            <a href="${pageContext.request.contextPath}/supplier/edit/${supplier.supplierId}" class="btn btn-sm btn-outline-primary me-1" title="Edit">
              <i class="fas fa-edit"></i>
            </a>
            <a href="${pageContext.request.contextPath}/supplier/delete/${supplier.supplierId}" class="btn btn-sm btn-outline-danger" title="Delete" onclick="return confirm('Are you sure you want to delete this supplier?');">
              <i class="fas fa-trash-alt"></i>
            </a>
          </td>
        </tr>
      </c:forEach>
      <c:if test="${empty suppliers}">
        <tr>
          <td colspan="5" class="text-center">No suppliers found.</td>
        </tr>
      </c:if>
      </tbody>
    </table>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/js/all.min.js"></script>

</body>
</html>
