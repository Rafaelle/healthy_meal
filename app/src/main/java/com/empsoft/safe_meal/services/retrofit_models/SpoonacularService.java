package com.empsoft.safe_meal.services.retrofit_models;

import com.empsoft.safe_meal.services.ISpoonacularService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by caynan on 17/02/17.
 */

public class SpoonacularService {
    private String MASHAPE_KEY = "";

    // Trailing slash is needed
    public static final String BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/";
    public static final String CONTENT_TYPE_HEADER = "application/json";
    public static final String ACCEPT_HEADER = "application/json";

    private ISpoonacularService spoonacularService;
    private static final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static final OkHttpClient.Builder client = new OkHttpClient.Builder();

    static {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(logging);
        client.readTimeout(20, TimeUnit.SECONDS);
        client.connectTimeout(20, TimeUnit.SECONDS);
        client.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
    }

    public SpoonacularService(final String mashapeKey) {
        // TODO: handle special cases when Key is empty or invalid.
        MASHAPE_KEY = mashapeKey;
        createRetrofit();
    }

    private void createRetrofit() {
        spoonacularService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build().create(ISpoonacularService.class);
    }

    public void mapIngredientsToGroceryProducts(final GroceryProductsMapper groceryProductsMapper,
                                                final Callback<List<GroceryProducts>> callback) {

        Call<List<GroceryProducts>> call = spoonacularService.groceryProducts(
                MASHAPE_KEY,
                CONTENT_TYPE_HEADER,
                ACCEPT_HEADER,
                groceryProductsMapper);

        call.enqueue(callback);
    }

    public void findRecipesByIngredients(final IngredientsMapper ingredientsMapper,
                                                final Callback<List<Recipe>> callback) {


        Call<List<Recipe>> call = spoonacularService.findRecipesByIngredients(
                MASHAPE_KEY,
                CONTENT_TYPE_HEADER,
                ACCEPT_HEADER,
                ingredientsMapper.isFillIngredients(),
                ingredientsMapper.getIngredientsAsString(","),
                ingredientsMapper.isLimitLicense(),
                ingredientsMapper.getNumber(),
                ingredientsMapper.getRanking());

        call.enqueue(callback);
    }

    public void getRecipeInformation(final RecipeInformationMapper recipeInformationMapper,
                                         final Callback<RecipeInformation> callback) {

        Call<RecipeInformation> call = spoonacularService.getRecipeInformation(
                MASHAPE_KEY,
                CONTENT_TYPE_HEADER,
                ACCEPT_HEADER,
                recipeInformationMapper.getId(),
                recipeInformationMapper.isIncludeNutrition()
        );

        call.enqueue(callback);
    }

    public void searchComplex(final ComplexSearchMapper complexSearchMapper,
                                final Callback<ComplexSearchResult>  callback){

        Call<ComplexSearchResult> call = spoonacularService.searchComplex(
                MASHAPE_KEY,
                CONTENT_TYPE_HEADER,
                ACCEPT_HEADER,
                complexSearchMapper.getAddRecipeInformation(),
                complexSearchMapper.getCuisine(),
                complexSearchMapper.getDiet(),
                complexSearchMapper.getExcludeIngredients(),
                complexSearchMapper.isFillIngredients(),
                complexSearchMapper.getIncludeIngredients(),
                complexSearchMapper.isInstructionsRequired(),
                complexSearchMapper.getIntolerances(),
                complexSearchMapper.isLimitLicense(),
                complexSearchMapper.getMaxCalories(),
                complexSearchMapper.getMaxCarbs(),
                complexSearchMapper.getMaxFat(),
                complexSearchMapper.getMaxProtein(),
                complexSearchMapper.getMinCalories(),
                complexSearchMapper.getMinCarbs(),
                complexSearchMapper.getMinFat(),
                complexSearchMapper.getMinProtein(),
                complexSearchMapper.getNumber(),
                complexSearchMapper.getOffset(),
                complexSearchMapper.getQuery(),
                complexSearchMapper.getRanking(),
                complexSearchMapper.getType()
        );
        call.enqueue(callback);

    }


    public void getAnalyzedRecipeInstructions(final AnalyzedRecipeInstructionsMapper analyzedRecipeInstructionsMapper,
                                              final Callback<List<AnalyzedRecipeInstructions>> callback) {

        Call<List<AnalyzedRecipeInstructions>> call = spoonacularService.getAnalyzedRecipeInstructions(
                MASHAPE_KEY,
                CONTENT_TYPE_HEADER,
                ACCEPT_HEADER,
                analyzedRecipeInstructionsMapper.getId(),
                analyzedRecipeInstructionsMapper.isStepBreakdown()
        );

        call.enqueue(callback);
    }
}
