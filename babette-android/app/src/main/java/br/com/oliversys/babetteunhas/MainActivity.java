package br.com.oliversys.babetteunhas;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.oliversys.babetteunhas.fragments.BlogFragment;
import br.com.oliversys.babetteunhas.fragments.BuscarProfissionaisFragment;
import br.com.oliversys.babetteunhas.fragments.DicasFragment;
import br.com.oliversys.babetteunhas.fragments.FaleConoscoFragment;
import br.com.oliversys.babetteunhas.fragments.FavoritosFragment;
import br.com.oliversys.babetteunhas.fragments.PerfilUsuarioFragment;
import br.com.oliversys.babetteunhas.fragments.PromocoesFragment;
import pedepizza.oliversoft.com.br.babetteunhas.R;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerCallbacks,OnFragmentInteractionListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView drawerListView;
    private ArrayAdapter navigationDrawerAdapter;
    private List<NavDrawerItem> drawerItems;

    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        if (toolbar != null) {
            toolbar.setTitle("Babette Unhas");
            setSupportActionBar(toolbar);
        }
        initDrawer();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        View listViewLayout = findViewById(R.id.container);
        drawerListView = (ListView)listViewLayout.findViewById(R.id.left_drawer);
        View header = getLayoutInflater().inflate(R.layout.drawer_header,drawerListView,false);
        drawerListView.addHeaderView(header);
        View footer = getLayoutInflater().inflate(R.layout.drawer_footer,drawerListView,false);
        drawerListView.addFooterView(footer);
        drawerItems = new ArrayList<NavDrawerItem>();
        addItemsToDrawer();

        navigationDrawerAdapter = new NavDrawerListAdapter(this,drawerItems);
        drawerListView.setAdapter(navigationDrawerAdapter);
    }

    private void initDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void addItemsToDrawer(){
        drawerItems.add(new NavDrawerItem(getString(R.string.title_section0),R.drawable.ic_perfil));
        drawerItems.add(new NavDrawerItem(getString(R.string.title_section1),R.drawable.ic_favoritos));
        drawerItems.add(new NavDrawerItem(getString(R.string.title_section2),R.drawable.ic_busca));
        drawerItems.add(new NavDrawerItem(getString(R.string.title_section3),R.drawable.ic_dicas));
        drawerItems.add(new NavDrawerItem(getString(R.string.title_section4),R.drawable.ic_promo));
        drawerItems.add(new NavDrawerItem(getString(R.string.title_section5), R.drawable.ic_blog));
        drawerItems.add(new NavDrawerItem(getString(R.string.title_section6), R.drawable.ic_mail));
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.title_section0);
                break;
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_blog || id == R.id.menu_buscar || id == R.id.menu_dicas
                || id == R.id.menu_fale || id == R.id.menu_favoritos || id == R.id.menu_perfil
                || id == R.id.menu_promo || id == R.id.menu_config){
            return true;
        }

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment mFragment = null;
        switch (position){
            case 0:
                mFragment = PerfilUsuarioFragment.newInstance(0);
                break;
            case 1:
                mFragment = FavoritosFragment.newInstance(1);
                break;
            case 2:
                mFragment = BuscarProfissionaisFragment.newInstance(2);
                break;
            case 3:
                mFragment = DicasFragment.newInstance(3);
                break;
            case 4:
                mFragment = PromocoesFragment.newInstance(4);
                break;
            case 5:
                mFragment = BlogFragment.newInstance(5);
                break;
            case 6:
                mFragment = FaleConoscoFragment.newInstance(6);
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.drawer_layout, mFragment)
                .addToBackStack(null)
                .commit();
    }

    public void replaceFragment(EnumFragmentNames frag, int sectionNumber){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragmentFinded = null;
        switch (frag) {
            case PerfilUsuarioFragment:
                fragmentFinded = PerfilUsuarioFragment.newInstance(sectionNumber);
                break;
            case FavoritosFragment:
                fragmentFinded = FavoritosFragment.newInstance(sectionNumber);
                break;
            case BuscarProfissionaisFragment:
                fragmentFinded = BuscarProfissionaisFragment.newInstance(sectionNumber);
                break;
            case BlogFragment:
                fragmentFinded = BlogFragment.newInstance(sectionNumber);
                break;
            case DicasFragment:
                fragmentFinded = DicasFragment.newInstance(sectionNumber);
                break;
            case FaleConoscoFragment:
                fragmentFinded = FaleConoscoFragment.newInstance(sectionNumber);
                break;
            case PromocoesFragment:
                fragmentFinded = PromocoesFragment.newInstance(sectionNumber);
                break;
        }
        drawerToggle.setDrawerIndicatorEnabled(false);
        fragmentManager.beginTransaction()
                .replace(R.id.drawer_layout, fragmentFinded).addToBackStack(null).commit();
    }
}
