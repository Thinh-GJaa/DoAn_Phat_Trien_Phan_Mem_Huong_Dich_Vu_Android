package com.example.prudentialfinance.API;

import com.example.prudentialfinance.Container.Accounts.AccountBalanceResponse;
import com.example.prudentialfinance.Container.Accounts.AccountCreate;
import com.example.prudentialfinance.Container.Accounts.AccountDelete;
import com.example.prudentialfinance.Container.Accounts.AccountEdit;
import com.example.prudentialfinance.Container.Accounts.AccountGetAll;
import com.example.prudentialfinance.Container.Accounts.AccountGetById;
import com.example.prudentialfinance.Container.Accounts.AccountMonthlyResponse;
import com.example.prudentialfinance.Container.CategoryMonthlyResponse;
import com.example.prudentialfinance.Container.GoalAdd;
import com.example.prudentialfinance.Container.GoalGetAll;
import com.example.prudentialfinance.Container.CategoryAdd;
import com.example.prudentialfinance.Container.NotificationGetAll;
import com.example.prudentialfinance.Container.NotificationResponse;
import com.example.prudentialfinance.Container.Report.CategoryReportResponse;
import com.example.prudentialfinance.Container.Report.IncomeVsExpenseResponse;
import com.example.prudentialfinance.Container.Report.TransactionByCategoryResponse;
import com.example.prudentialfinance.Container.Settings.AvatarUpload;
import com.example.prudentialfinance.Container.CategoryGetAll;
import com.example.prudentialfinance.Container.Settings.EmailSettingsResponse;
import com.example.prudentialfinance.Container.HomeLatestTransactions;
import com.example.prudentialfinance.Container.Login;
import com.example.prudentialfinance.Container.ReportTotalBalance;
import com.example.prudentialfinance.Container.Settings.SiteSettingsResponse;
import com.example.prudentialfinance.Container.budgets.budgetGET.AmountGet;
import com.example.prudentialfinance.Container.budgets.budgetGET.BudgetAdd;
import com.example.prudentialfinance.Container.budgets.budgetGET.Root;

import com.example.prudentialfinance.Container.SimpleResponse;
import com.example.prudentialfinance.Container.Transactions.TransactionCreate;
import com.example.prudentialfinance.Container.Transactions.TransactionGetTotal;
import com.example.prudentialfinance.Container.Transactions.TransactionRemove;
import com.example.prudentialfinance.Container.Transactions.TransactionUpdate;
import com.example.prudentialfinance.Container.Users.UserAdd;
import com.example.prudentialfinance.Container.Users.UserGetAll;
import com.example.prudentialfinance.Model.AccountMonthly;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface HTTPRequest {

    /**
     * Attention: we should utilize String data type, even parameters are passed to
     * function in API maybe as Integer, Double, Long, .. and so forth
     *
     * Using String data type helps us to reduce errors when Retrofit return result equals oh but
     * doesn't return error message
     * */

    /*Login*/
    @FormUrlEncoded
    @POST("login")
    Call<Login> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("login/google")
    Call<Login> loginGoogle(@Field("id_token") String id_token);

    @FormUrlEncoded
    @POST("login/facebook")
    Call<Login> loginFacebook(@Field("access_token") String access_token);


    //Recovery password
    @FormUrlEncoded
    @POST("recovery")
    Call<Login> recovery(@Field("email") String email);

    //RESET password
    @FormUrlEncoded
    @POST("reset")
    Call<Login> process_reset(@Field("email") String email,@Field("code") String otp,@Field("action") String action);
    @FormUrlEncoded
    @POST("reset")
    Call<Login> reset_pass(@Field("email") String email, @Field("hash") String hash,@Field("password") String password,@Field("password-confirm") String password_confirm,@Field("action") String action);

    /*Register*/
    @FormUrlEncoded
    @POST("signup")
    Call<Login> signup(
            @Field("firstname") String firstname, @Field("lastname") String lastname,
            @Field("email") String email, @Field("password") String password,
            @Field("password-confirm") String passwordConfirm
    );


    // Profile
    @GET("profile")
    Call<Login> profile(@Header("Authorization") String authorization);

    @FormUrlEncoded
    @POST("profile")
    Call<Login> updateProfile(@HeaderMap Map<String, String> headers,
                              @Field("action") String action,
                              @Field("firstname") String firstname,
                              @Field("lastname") String lastname);

    @Multipart
    @POST("profile")
    Call<AvatarUpload> uploadAvatar(@Header("Authorization") String authorization,
                                    @Part("action") RequestBody action,
                                    @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("profile")
    Call<SimpleResponse> updateLanguage(@HeaderMap Map<String, String> headers,
                                        @Field("action") String action,
                                        @Field("langcode") String langcode);


    @FormUrlEncoded
    @POST("change-password")
    Call<Login> changePassword(@HeaderMap Map<String, String> headers,
                              @Field("password") String newPass, @Field("password-confirm") String confirmPass,
                              @Field("current-password") String currentPass);

    /** Application settings*/


    @GET("settings/site")
    Call<SiteSettingsResponse> getSiteSettings(@HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST("settings/site")
    Call<SiteSettingsResponse> saveSiteSettings(@HeaderMap Map<String, String> headers,
                                                @Field("action") String action,
                                                @Field("site_name") String site_name,
                                                @Field("site_slogan") String site_slogan,
                                                @Field("site_description") String site_description,
                                                @Field("site_keywords") String site_keywords,
                                                @Field("logotype") String logotype,
                                                @Field("logomark") String logomark,
                                                @Field("language") String language,
                                                @Field("currency") String currency);

    @GET("settings/smtp")
    Call<EmailSettingsResponse> getEmailSettings(@HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST("settings/smtp")
    Call<EmailSettingsResponse> saveEmailSettings(@HeaderMap Map<String, String> headers,
                                                  @Field("action") String action,
                                                  @Field("host") String host,
                                                  @Field("port") String port,
                                                  @Field("encryption") String encryption,
                                                  @Field("auth") Boolean auth,
                                                  @Field("username") String username,
                                                  @Field("password") String password,
                                                  @Field("from") String from);

    /***************************ACCOUNT***************************/
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @GET("accounts")
    Call<AccountGetAll> accountGetAll(@Header("Authorization") String authorization);

    @GET("accounts")
    Call<AccountGetAll> accountGetAll2(@HeaderMap Map<String, String> headers);

    @GET("accounts")
    Call<AccountGetAll> accountGetAll3(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> options);

    /*get by id*/
    @GET("accounts/{id}")
    Call<AccountGetById> accountGetById(@HeaderMap Map<String, String> headers, @Path("id") String id);

    /*create*/
    @FormUrlEncoded
    @POST("accounts")
    Call<AccountCreate> accountCreate(@HeaderMap Map<String, String> headers,
                                      @Field("name") String name,
                                      @Field("balance") String balance,
                                      @Field("description") String description,
                                      @Field("accountnumber") String accountnumber);

    /*edit*/
    @FormUrlEncoded
    @PUT("accounts/{id}")
    Call<AccountEdit> accountUpdate(@HeaderMap Map<String, String> headers,
                                  @Path("id") int id,
                                  @Field("name") String name,
                                  @Field("balance") String balance,
                                  @Field("description") String description,
                                  @Field("accountnumber") String accountnumber);

    @FormUrlEncoded
    @PUT("accounts/{id}")
    Call<AccountEdit> accountUpdate2(@HeaderMap Map<String, String> headers,
                                    @QueryMap Map<String, String> parameters);


    @DELETE("accounts/{id}")
    Call<AccountDelete> accountDelete(@HeaderMap Map<String, String> headers, @Path("id") int id);

    @GET("home/accountbalance")
    Call<AccountBalanceResponse> accountBalance(@HeaderMap Map<String, String> headers);


    @GET("accounts/accountbalancebymonth/{id}")
    Call<AccountMonthlyResponse> accountBalanceMonthly(@HeaderMap Map<String, String> headers, @Path("id") int id);




    /***************************HOME*********************************/
    @GET("home/latestall")
    Call<HomeLatestTransactions> homeLatestTransactions(@HeaderMap Map<String, String> headers,
                                                        @QueryMap Map<String, String> parameters);

    @GET("home/latestall")
    Call<HomeLatestTransactions> homeLatestTransactions2(@HeaderMap Map<String, String> headers,
                                                        @Query("search") String search,
                                                        @Query("start") int start,
                                                        @Query("length") int length,
                                                        @Query("order[column]") String column,
                                                        @Query("order[dir]") String dir);


    //GOAL--------------------------------------------------------------------------------------
    @GET("goals")
    Call<GoalGetAll> getGoals(@HeaderMap Map<String, String> headers,
                              @Query("search") String search,
                              @Query("start") int start,
                              @Query("length") int length,
                              @Query("order[column]") String column,
                              @Query("order[dir]") String dir,
                              @Query("status") int status ,
                              @Query("dateFrom") String dateFrom,
                              @Query("dateTo") String dateTo);

    @DELETE("goals/{id}")
    Call<GoalAdd> removeGoal(@HeaderMap Map<String, String> headers, @Path("id") int id);

    @FormUrlEncoded
    @POST("goals")
    Call<GoalAdd> addNewGoal(@HeaderMap Map<String, String> headers,
                             @Field("name") String name,
                             @Field("balance") long balance,
                             @Field("amount") long amount,
                             @Field("deadline") String deadline);

    @FormUrlEncoded
    @POST("goals/{id}")
    Call<GoalAdd> depositGoal(@HeaderMap Map<String, String> headers,
                              @Path("id") int id,
                             @Field("deposit") int deposit,
                             @Field("action") String action
                             );

    @FormUrlEncoded
    @PUT("goals/{id}")
    Call<GoalAdd> editGoal(@HeaderMap Map<String, String> headers,
                           @Path("id") int id,
                           @Field("name") String name,
                           @Field("balance") long balance,
                           @Field("amount") long amount,
                           @Field("deadline") String deadline);

    /***************************CATEGORY*********************************/
    @GET("incomecategories")
    Call<CategoryGetAll> searchIncomeCategories(@HeaderMap Map<String, String> headers,
                                                @Query("search") String search,
                                                @Query("start") int start,
                                                @Query("length") int length,
                                                @Query("order[column]") String column,
                                                @Query("order[dir]") String dir);

    @GET("incomecategories")
    Call<CategoryGetAll> retrieveInflowCategories(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> parameters);

    @GET("expensecategories")
    Call<CategoryGetAll> retrieveOutflowCategories(@HeaderMap Map<String, String> headers, @QueryMap Map<String, String> parameters);

    @DELETE("incomecategories/{id}")
    Call<CategoryAdd> removeIncomeCategories(@HeaderMap Map<String, String> headers, @Path("id") int id);

    @FormUrlEncoded
    @POST("incomecategories")
    Call<CategoryAdd> addNewIncomeCategory(@HeaderMap Map<String, String> headers,
                                           @Field("name") String name,
                                           @Field("description") String description,
                                           @Field("color") String color);

    @FormUrlEncoded
    @PUT("incomecategories/{id}")
    Call<CategoryAdd> editIncomeCategory(@HeaderMap Map<String, String> headers,
                                         @Path("id") int id,
                                         @Field("name") String name,
                                         @Field("description") String description,
                                         @Field("color") String color);





    @GET("expensecategories")
    Call<CategoryGetAll> searchExpenseCategories(@HeaderMap Map<String, String> headers,
                                                 @Query("search") String search,
                                                 @Query("start") int start,
                                                 @Query("length") int length,
                                                 @Query("order[column]") String column,
                                                 @Query("order[dir]") String dir);
    @DELETE("expensecategories/{id}")
    Call<CategoryAdd> removeExpenseCategories(@HeaderMap Map<String, String> headers, @Path("id") int id);

    @FormUrlEncoded
    @POST("expensecategories")
    Call<CategoryAdd> addNewExpenseCategory(@HeaderMap Map<String, String> headers,
                                            @Field("name") String name,
                                            @Field("description") String description,
                                            @Field("color") String color);

    @FormUrlEncoded
    @PUT("expensecategories/{id}")
    Call<CategoryAdd> editExpenseCategory(@HeaderMap Map<String, String> headers,
                                          @Path("id") int id,
                                          @Field("name") String name,
                                          @Field("description") String description,
                                          @Field("color") String color);


    @GET("home/incomevsexpense")
    Call<IncomeVsExpenseResponse> getReportGroupByDate(@HeaderMap Map<String, String> headers,
                                                 @Query("type") String type,
                                                 @Query("date") String date);

    /***************************USER***************************/
    @GET("users")
    Call<UserGetAll> searchUsers(@HeaderMap Map<String, String> headers,
                                             @Query("search") String search,
                                             @Query("start") int start,
                                             @Query("length") int length,
                                             @Query("order[column]") String column,
                                             @Query("order[dir]") String dir);
    @DELETE("users/{id}")
    Call<UserAdd> removeUser(@HeaderMap Map<String, String> headers, @Path("id") int id);

    @PATCH("users/{id}")
    Call<UserAdd> restoreUser(@HeaderMap Map<String, String> headers, @Path("id") int id);

    @FormUrlEncoded
    @POST("users")
    Call<UserAdd> addUser(@HeaderMap Map<String, String> headers,
                          @Field("email") String email,
                          @Field("firstname") String firstname,
                          @Field("lastname") String lastname,
                          @Field("account_type") String account_type,
                          @Field("is_active") boolean is_active
    );

    @FormUrlEncoded
    @PUT("users/{id}")
    Call<UserAdd> updateUser(@HeaderMap Map<String, String> headers,
                             @Path("id") int id,
                             @Field("firstname") String firstname,
                             @Field("lastname") String lastname,
                             @Field("account_type") String account_type,
                             @Field("is_active") boolean is_active
    );



    /***************************REPORT***************************/
    @GET("report/totalBalance")
    Call<ReportTotalBalance> reportTotalBalace(@HeaderMap Map<String, String> headers,
                                               @Query("date") String date);

    @GET("home/category/income")
    Call<CategoryReportResponse> incomeByDate(@HeaderMap Map<String, String> headers,
                                              @Query("date") String date);

    @GET("home/category/expense")
    Call<CategoryReportResponse> expenseByDate(@HeaderMap Map<String, String> headers,
                                               @Query("date") String date);

    @GET("report/transactions")
    Call<TransactionByCategoryResponse> getTransactionByCategory(@HeaderMap Map<String, String> headers,
                                                                 @Query("search") String search,
                                                                 @Query("start") int start,
                                                                 @Query("length") int length,
                                                                 @Query("order[column]") String column,
                                                                 @Query("order[dir]") String dir,
                                                                 @Query("fromdate") String fromdate,
                                                                 @Query("todate") String todate,
                                                                 @Query("category_id") int category_id);

    @GET("report/categorymonthly")
    Call<CategoryMonthlyResponse> getCategoryMonthly(@HeaderMap Map<String, String> headers,
                                                     @Query("search") String search,
                                                     @Query("start") int start,
                                                     @Query("length") int length,
                                                     @Query("order[column]") String column,
                                                     @Query("order[dir]") String dir,
                                                     @Query("type") int type);


    /***************************TRANSACTIONS***************************/
    @GET("transactions/income/gettotal")
    Call<TransactionGetTotal> transactionIncomeTotal(@HeaderMap Map<String, String> headers);

    @GET("transactions/expense/gettotal")
    Call<TransactionGetTotal> transactionExpenseTotal(@HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST("transactions/income")
    Call<TransactionCreate> transactionCreate(@HeaderMap Map<String, String> headers,
                                              @Field("category_id") String categoryId,
                                              @Field("account_id") String accountId,
                                              @Field("name") String name,
                                              @Field("amount") String amount,
                                              @Field("reference") String reference,
                                              @Field("transactiondate") String transactionDate,
                                              @Field("type") String type,
                                              @Field("description") String description);

    @DELETE("transactions/{id}")
    Call<TransactionRemove> transactionRemove(@HeaderMap Map<String, String> headers,
                                              @Path("id") String id);

  
    @FormUrlEncoded
    @PUT("transactions/{id}")
    Call<TransactionUpdate> transactionUpdate(@HeaderMap Map<String, String> headers,
                                              @Path("id") int id,
                                              @Field("category_id") String categoryId,
                                              @Field("account_id") String accountId,
                                              @Field("name") String name,
                                              @Field("amount") String amount,
                                              @Field("reference") String reference,
                                              @Field("transactiondate") String transactionDate,
                                              @Field("type") String type,
                                              @Field("description") String description);

  
  
    /***************************BUDGET***************************/
    @GET("budgets")
    Call<Root> budget(@HeaderMap Map<String, String> headers,
                      @Query("search") String search,
                      @Query("start") int start,
                      @Query("length") int length,
                      @Query("order[column]") String column,
                      @Query("order[dir]") String dir);
    @DELETE("budgets/{id}")
    Call<Root> removeBudget(@HeaderMap Map<String, String> headers, @Path("id") int id);

    @PATCH("budgets/{id}")
    Call<BudgetAdd> restoreBudget(@HeaderMap Map<String, String> headers, @Path("id") int id);

    @FormUrlEncoded
    @POST("budgets")
    Call<BudgetAdd> getTransactionByDate(@HeaderMap Map<String, String> headers,
                              @Field("amount") String amount,
                              @Field("description") String description,
                              @Field("category_id") String category_id,
                              @Field("month") String month,
                              @Field("year") String year
    );
    @FormUrlEncoded
    @POST("budgets")
    Call<BudgetAdd> addBudget(@HeaderMap Map<String, String> headers,
                              @Field("amount") String amount,
                              @Field("description") String description,
                              @Field("category_id") String category_id,
                              @Field("month") String month,
                              @Field("year") String year
    );

    @GET("budgets/gettransactionbydate")
    Call<AmountGet> getAmount(@HeaderMap Map<String, String> headers,
                              @Query("category_id") String categoryId,
                              @Query("date") String date);

    @FormUrlEncoded
    @PUT("budgets/{id}")
    Call<BudgetAdd> updateBudget(@HeaderMap Map<String, String> headers,
                             @Path("id") String id,
                             @Field("amount") String amount,
                             @Field("description") String description,
                             @Field("category_id") String category_id,
                             @Field("month") String month,
                             @Field("year") String year
    );
  
  

    // Notification
    @GET("notifications")
    Call<NotificationGetAll> getNotification(@HeaderMap Map<String, String> headers);

    @POST("notifications")
    Call<NotificationResponse> maskedAsRead(@HeaderMap Map<String, String> headers);

    @GET("notifications/{id}")
    Call<NotificationResponse> maskedAsReadOne(@HeaderMap Map<String, String> headers, @Path("id") int id);

}
