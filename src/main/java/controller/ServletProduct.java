//package controller;
//
//import dao.ProductDAO;
//import model.Product;
//import service.ProductService;
//import service.ProductServiceImpl;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "ServletProduct", urlPatterns = "/ServletProduct")
//public class ServletProduct extends HttpServlet {
//    private ProductService productService = new ProductServiceImpl();
//
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        if (action == null) {
//            action = "";
//        }
//        switch (action) {
//            case "create":
//                showCreateProduct(request, response);
//                break;
//            case "edit":
//                showeditform(request, response);
//                break;
//            case "view":
//                viewProduct(request,response);
//                break;
//            case "delete":
//                showDeleteProduct(request,response);
//                break;
//            case "search":
//                showFindName(request,response);
//                break;
//            default:
//                listProducts(request, response);
//
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        if (action == null) {
//            action = "";
//        }
//        switch (action) {
//            case "create":
//                createProduct(request, response);
//                break;
//            case "edit":
//                updateEditForm(request,response);
//                break;
//            case "delete":
//                deleteProduct(request,response);
//                break;
//        }
//    }
//    private void showFindName(HttpServletRequest request, HttpServletResponse response) {
//        String name = request.getParameter("nameSearch");
//        List<Product> products = this.productService.findByName(name);
//        request.setAttribute("products", products);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
//        try {
//            requestDispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private void listProducts(HttpServletRequest request, HttpServletResponse response) {
//        List<Product> products = this.productService.findAll();
//        request.setAttribute("products", products);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
//        try {
//            requestDispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void showCreateProduct(HttpServletRequest request, HttpServletResponse response) {
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
//        try {
//            requestDispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.getStackTrace();
//        }
//    }
//
//    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
//        int id = (int) (Math.random() * 10000);
//        String name = request.getParameter("name");
//        float price =Float.parseFloat(request.getParameter("price"));
//        String description = request.getParameter("description");
//        String nsx = request.getParameter("nsx");
//        Product product = new Product(id, name, price, description, nsx);
//        this.productService.save(product);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
//        request.setAttribute("message", "New customer was created");
//        try {
//            requestDispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.getStackTrace();
//        }
//    }
//
//    private void showeditform(HttpServletRequest request, HttpServletResponse response) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Product product = this.productService.findById(id);
//        RequestDispatcher requestDispatcher;
//        if (product == null) {
//            requestDispatcher = request.getRequestDispatcher("error-404.jsp");
//        } else {
//            request.setAttribute("product", product);
//            requestDispatcher = request.getRequestDispatcher("product/edit.jsp");
//        }
//        try {
//            requestDispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private void updateEditForm(HttpServletRequest request,HttpServletResponse response){
//        int id = Integer.parseInt(request.getParameter("id"));
//
//        String name = request.getParameter("name");
//        float price = Float.parseFloat(request.getParameter("price"));
//        String description = request.getParameter("description");
//        String nsx = request.getParameter("nsx");
//        Product product = this.productService.findById(id);
//        RequestDispatcher requestDispatcher;
//        if (product==null){
//            requestDispatcher=request.getRequestDispatcher("error-404.jsp");
//        }else {
//            product.setId(id);
//            product.setName(name);
//            product.setPrice(price);
//            product.setDescription(description);
//            product.setNsx(nsx);
//            this.productService.update(id,product);
//            request.setAttribute("product",product);
//            request.setAttribute("message", "Product information was updated");
//            requestDispatcher = request.getRequestDispatcher("product/edit.jsp");
//        }
//        try {
//            requestDispatcher.forward(request,response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private void viewProduct(HttpServletRequest request, HttpServletResponse response) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Product product = this.productService.findById(id);
//        RequestDispatcher dispatcher;
//        if(product == null){
//            dispatcher = request.getRequestDispatcher("error-404.jsp");
//        } else {
//            request.setAttribute("product", product);
//            dispatcher = request.getRequestDispatcher("product/view.jsp");
//        }
//        try {
//            dispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Product product = this.productService.findById(id);
//        RequestDispatcher dispatcher;
//        if(product == null){
//            dispatcher = request.getRequestDispatcher("error-404.jsp");
//        } else {
//            this.productService.remove(id);
//            try {
//                response.sendRedirect("/ServletProduct");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    private void showDeleteProduct(HttpServletRequest request,HttpServletResponse response){
//        int id = Integer.parseInt(request.getParameter("id"));
//        Product product = this.productService.findById(id);
//        RequestDispatcher dispatcher;
//        if(product== null){
//            dispatcher = request.getRequestDispatcher("error-404.jsp");
//        } else {
//            request.setAttribute("product",product);
//            dispatcher = request.getRequestDispatcher("product/delete.jsp");
//        }
//        try {
//            dispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
