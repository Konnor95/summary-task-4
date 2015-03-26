package ua.nure.bekuzarov.SummaryTask4.constant;

/**
 * Contains relative URLs of all servlets.
 */
public final class Actions {

    /**
     * Main module.
     */
    public static final class Main {

        public static final String INDEX = "/";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
        public static final String PROFILE = "/profile";
        public static final String REGISTRATION = "/registration";

        /**
         * Books actions.
         */
        public static final class Books {

            public static final String LIST = "/books";
            public static final String ITEM = "/book";

            private Books() {

            }
        }

        /**
         * Authors actions.
         */
        public static final class Authors {

            public static final String ITEM = "/author";

            private Authors() {

            }
        }


        /**
         * Cart actions.
         */
        public static final class Cart {

            public static final String CART = "/cart";
            public static final String UPDATE_ITEM = "/cart/update";
            public static final String DELETE_ITEM = "/cart/delete";

            private Cart() {

            }
        }

        private Main() {
        }

    }

    /**
     * Library module.
     */
    public static final class Library {

        public static final String INDEX = "/library";
        public static final String LOGIN = INDEX + "/login";
        public static final String LOGOUT = INDEX + "/logout";
        public static final String ADD_ORDER = INDEX + "/orders/add";
        public static final String USER_BOOKS = INDEX + "/user-books";

        /**
         * Ordered books actions.
         */
        public static final class Ordered {

            public static final String LIST = INDEX + "/orders/ordered";
            public static final String ACCEPT = INDEX + "/orders/ordered/accept";
            public static final String REPORT = INDEX + "/orders/ordered/*";

            private Ordered() {
            }

        }

        /**
         * Checked out books actions.
         */
        public static final class CheckedOut {

            public static final String LIST = INDEX + "/orders/checked-out";
            public static final String REPORT = INDEX + "/orders/checked-out/*";
            public static final String COMPLETE = INDEX + "/orders/checked-out/complete";

            private CheckedOut() {
            }

        }

        /**
         * Completed orders actions.
         */
        public static final class Completed {

            public static final String LIST = INDEX + "/orders/completed";
            public static final String REPORT = INDEX + "/orders/completed/*";

            private Completed() {
            }

        }

        /**
         * Reading rooms actions.
         */
        public static final class ReadingRooms {

            public static final String LIST = INDEX + "/orders/reading-rooms";
            public static final String COMPLETE = INDEX + "/orders/reading-rooms/complete";
            public static final String REPORT = INDEX + "/orders/reading-rooms/*";


            private ReadingRooms() {
            }

        }


        private Library() {
        }

    }

    /**
     * Dashboard module.
     */
    public static final class Dashboard {

        public static final String INDEX = "/dashboard";
        public static final String LOGIN = "/dashboard/login";
        public static final String LOGOUT = "/dashboard/logout";

        /**
         * Librarians managing actions.
         */
        public static final class Librarians {

            public static final String LIST = INDEX + "/users/librarians";
            public static final String ADD = INDEX + "/users/librarians/add";
            public static final String EDIT = INDEX + "/users/librarians/edit";
            public static final String DELETE = INDEX + "/users/librarians/delete";

            private Librarians() {
            }

        }

        /**
         * Administrators managing actions.
         */
        public static final class Administrators {

            public static final String LIST = INDEX + "/users/administrators";
            public static final String ADD = INDEX + "/users/administrators/add";
            public static final String EDIT = INDEX + "/users/administrators/edit";
            public static final String DELETE = INDEX + "/users/administrators/delete";


            private Administrators() {
            }

        }


        /**
         * Users (readers) managing actions.
         */
        public static final class Users {

            public static final String LIST = INDEX + "/users";
            public static final String CONFIRM = INDEX + "/users/confirm";
            public static final String NOT_CONFIRMED = INDEX + "/users/not-confirmed";
            public static final String BAN = INDEX + "/users/ban";
            public static final String BLACK_LIST = INDEX + "/users/black-list";
            public static final String UNBAN = INDEX + "/users/unban";
            public static final String OVERDUE = INDEX + "/users/overdue";
            public static final String EXTEND_OVERDUE = OVERDUE + "/extend";
            public static final String EXTEND_EXISTING = INDEX + "/users/extend";

            private Users() {
            }

        }

        /**
         * Authors managing actions.
         */
        public static final class Authors {

            public static final String LIST = INDEX + "/authors";
            public static final String ADD = INDEX + "/authors/add";
            public static final String EDIT = INDEX + "/authors/edit";
            public static final String DELETE = INDEX + "/authors/delete";

            private Authors() {
            }

        }

        /**
         * Books managing actions.
         */
        public static final class Books {

            public static final String LIST = INDEX + "/books";
            public static final String ADD = INDEX + "/books/add";
            public static final String EDIT = INDEX + "/books/edit";
            public static final String DELETE = INDEX + "/books/delete";

            private Books() {
            }

        }

        /**
         * Publishers managing actions.
         */
        public static final class Publishers {

            public static final String LIST = INDEX + "/publishers";
            public static final String ADD = INDEX + "/publishers/add";
            public static final String EDIT = INDEX + "/publishers/edit";
            public static final String DELETE = INDEX + "/publishers/delete";

            private Publishers() {
            }

        }

        private Dashboard() {
        }

    }

    private Actions() {
    }
}
