import java.util.*;

// Clase que representa un producto en la tienda
class Producto {
    String codigo;
    String nombre;
    double precio;

    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }
}

// Clase que representa la tienda y sus funciones
class Tienda {
    Map<String, Producto> inventario;
    List<Producto> carrito;

    public Tienda() {
        inventario = new HashMap<>();
        carrito = new ArrayList<>();
    }

    // Método para cargar productos en el inventario
    public void cargarInventario(String codigo, String nombre, double precio) {
        Producto producto = new Producto(codigo, nombre, precio);
        inventario.put(codigo, producto);
    }

    // Método para buscar un producto por código o nombre en el inventario
    public Producto buscarProducto(String clave) {
        if (inventario.containsKey(clave)) {
            return inventario.get(clave);
        } else {
            for (Producto producto : inventario.values()) {
                if (producto.nombre.equalsIgnoreCase(clave)) {
                    return producto;
                }
            }
        }
        return null;
    }

    // Método para agregar un producto al carrito
    public void agregarAlCarrito(Producto producto) {
        carrito.add(producto);
    }

    // Método para calcular el total del carrito con IVA
    public double calcularTotalCarrito() {
        double total = 0;
        for (Producto producto : carrito) {
            total += producto.precio;
        }
        return total * 1.19; // Incluye el 19% de IVA
    }

    // Método para realizar una venta, calcula el total y muestra el monto con IVA
    public void realizarVenta() {
        double total = calcularTotalCarrito();
        System.out.println("Total de la venta (con IVA): $" + total);
        carrito.clear(); // Limpia el carrito después de la venta
    }
}

// Clase principal que contiene el punto de entrada del programa
public class Main {
    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        Scanner scanner = new Scanner(System.in);

        // Carga de productos de ejemplo en el inventario
        tienda.cargarInventario("P1", "Producto 1", 10.0);
        tienda.cargarInventario("P2", "Producto 2", 20.0);
        tienda.cargarInventario("P3", "Producto 3", 30.0);

        boolean continuar = true;
        while (continuar) {
            System.out.println("1. Consultar producto por código o nombre");
            System.out.println("2. Agregar producto al carrito");
            System.out.println("3. Realizar venta");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el código o nombre del producto: ");
                    scanner.nextLine(); // Limpia el búfer
                    String claveProducto = scanner.nextLine();
                    Producto productoEncontrado = tienda.buscarProducto(claveProducto);
                    if (productoEncontrado != null) {
                        System.out.println("Producto encontrado: " + productoEncontrado.nombre + " - Precio: $" + productoEncontrado.precio);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el código o nombre del producto a agregar al carrito: ");
                    scanner.nextLine(); // Limpia el búfer
                    String claveCarrito = scanner.nextLine();
                    Producto productoCarrito = tienda.buscarProducto(claveCarrito);
                    if (productoCarrito != null) {
                        tienda.agregarAlCarrito(productoCarrito);
                        System.out.println("Producto agregado al carrito.");
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                case 3:
                    if (!tienda.carrito.isEmpty()) {
                        tienda.realizarVenta();
                    } else {
                        System.out.println("El carrito está vacío.");
                    }
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        System.out.println("¡Gracias por usar la aplicación de la tienda!");
    }
}
