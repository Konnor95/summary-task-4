package ua.nure.bekuzarov.SummaryTask4.constant;

/**
 * Contains relative paths for all jsp pages.
 */
public final class Pages {

    private static final String PREFIX = "/WEB-INF/pages/";

    /**
     * Paths to JSP pages of main module.
     *
     * @see ua.nure.bekuzarov.SummaryTask4.constant.Actions.Main
     */
    public static final class Main {

        private static final String MAIN = PREFIX + "main/";
        public static final String INDEX = MAIN + "index.jsp";
        public static final String LOGIN = MAIN + "login.jsp";
        public static final String PROFILE = MAIN + "profile.jsp";
        public static final String REGISTRATION = MAIN + "registration.jsp";
        public static final String REGISTRATION_SUCCEED = MAIN + "registrationSucceed.jsp";

        /**
         * Paths to JSP pages of books actions.
         */
        public static final class Books {

            public static final String LIST = MAIN + "books/list.jsp";
            public static final String ITEM = MAIN + "books/item.jsp";
            public static final String CART = MAIN + "books/cart.jsp";

            private Books() {
            }

        }

        /**
         * Paths to JSP pages of authors actions.
         */
        public static final class Authors {

            public static final String ITEM = MAIN + "authors/item.jsp";

            private Authors() {
            }

        }

        private Main() {
        }

    }

    /**
     * Paths to JSP pages of library module.
     *
     * @see ua.nure.bekuzarov.SummaryTask4.constant.Actions.Library
     */
    public static final class Library {

        private static final String LIBRARY = PREFIX + "library/";
        public static final String INDEX = PREFIX + "library/index.jsp";
        public static final String LOGIN = PREFIX + "library/login.jsp";
        public static final String ADD_ORDER = PREFIX + "library/orders/add.jsp";
        public static final String USER_BOOKS = PREFIX + "library/orders/userBooks.jsp";

        /**
         * Paths to JSP pages of ordered books actions.
         */
        public static final class Ordered {

            public static final String LIST = LIBRARY + "orders/ordered/list.jsp";
            public static final String ACCEPT = LIBRARY + "orders/ordered/accept.jsp";

            private Ordered() {
            }

        }

        /**
         * Paths to JSP pages of checked out books actions.
         */
        public static final class CheckedOut {

            public static final String LIST = LIBRARY + "orders/checkedOut/list.jsp";
            public static final String COMPLETE = LIBRARY + "orders/checkedOut/complete.jsp";

            private CheckedOut() {
            }

        }

        /**
         * Paths to JSP pages of completed orders actions.
         */
        public static final class Completed {

            public static final String LIST = LIBRARY + "orders/completed/list.jsp";

            private Completed() {
            }

        }

        /**
         * Paths to JSP pages of reading rooms actions.
         */
        public static final class ReadingRooms {

            public static final String LIST = LIBRARY + "orders/readingRooms/list.jsp";
            public static final String COMPLETE = LIBRARY + "orders/readingRooms/complete.jsp";

            private ReadingRooms() {
            }

        }


        private Library() {
        }

    }

    /**
     * Paths to JSP pages of dashboard module.
     *
     * @see ua.nure.bekuzarov.SummaryTask4.constant.Actions.Dashboard
     */
    public static final class Dashboard {

        private static final String DASHBOARD = PREFIX + "dashboard/";
        public static final String INDEX = "/WEB-INF/pages/dashboard/index.jsp";
        public static final String LOGIN = "/WEB-INF/pages/dashboard/login.jsp";

        /**
         * Paths to JSP pages of managing librarians actions.
         */
        public static final class Librarians {

            public static final String LIST = DASHBOARD + "/librarians/list.jsp";
            public static final String ADD = DASHBOARD + "/librarians/add.jsp";
            public static final String EDIT = DASHBOARD + "/librarians/edit.jsp";
            public static final String DELETE = DASHBOARD + "/librarians/delete.jsp";

            private Librarians() {
            }
        }

        /**
         * Paths to JSP pages of managing administrators actions.
         */
        public static final class Administrators {

            public static final String LIST = DASHBOARD + "/administrators/list.jsp";
            public static final String ADD = DASHBOARD + "/administrators/add.jsp";
            public static final String EDIT = DASHBOARD + "/administrators/edit.jsp";
            public static final String DELETE = DASHBOARD + "/administrators/delete.jsp";

            private Administrators() {
            }
        }


        /**
         * Paths to JSP pages of managing users(readers) actions.
         */
        public static final class Users {

            public static final String CONFIRM = DASHBOARD + "/users/confirm.jsp";
            public static final String NOT_CONFIRMED = DASHBOARD + "/users/notConfirmed.jsp";
            public static final String LIST = DASHBOARD + "/users/list.jsp";
            public static final String BAN = DASHBOARD + "/users/ban.jsp";
            public static final String UNBAN = DASHBOARD + "/users/unban.jsp";
            public static final String BLACK_LIST = DASHBOARD + "/users/blackList.jsp";
            public static final String OVERDUE_LIST = DASHBOARD + "/users/overdue.jsp";
            public static final String EXTEND = DASHBOARD + "/users/extend.jsp";

            private Users() {
            }
        }

        /**
         * Paths to JSP pages of managing authors actions.
         */
        public static final class Authors {

            public static final String LIST = DASHBOARD + "authors/list.jsp";
            public static final String ADD = DASHBOARD + "authors/add.jsp";
            public static final String EDIT = DASHBOARD + "authors/edit.jsp";
            public static final String DELETE = DASHBOARD + "authors/delete.jsp";

            private Authors() {
            }

        }

        /**
         * Paths to JSP pages of managing books actions.
         */
        public static final class Books {

            private static final String BOOKS = DASHBOARD + "books/";

            public static final String LIST = BOOKS + "list.jsp";
            public static final String ADD = BOOKS + "add.jsp";
            public static final String EDIT = BOOKS + "edit.jsp";
            public static final String DELETE = BOOKS + "delete.jsp";

            private Books() {

            }

        }

        /**
         * Paths to JSP pages of managing publishers actions.
         */
        public static final class Publishers {

            private static final String PUBLISHERS = DASHBOARD + "publishers/";

            public static final String ADD = PUBLISHERS + "add.jsp";
            public static final String DELETE = PUBLISHERS + "delete.jsp";
            public static final String EDIT = PUBLISHERS + "edit.jsp";
            public static final String LIST = PUBLISHERS + "list.jsp";

            private Publishers() {

            }
        }

        private Dashboard() {

        }

    }

    private Pages() {
    }


}
