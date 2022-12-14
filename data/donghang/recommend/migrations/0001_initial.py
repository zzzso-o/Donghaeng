# Generated by Django 4.0.7 on 2022-09-25 08:54

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='PlaceCommon',
            fields=[
                ('common_no', models.BigAutoField(primary_key=True, serialize=False)),
                ('content_id', models.TextField(blank=True, null=True)),
                ('content_type_id', models.TextField(blank=True, null=True)),
                ('tel', models.TextField(blank=True, null=True)),
                ('title', models.TextField(blank=True, null=True)),
                ('first_image1', models.TextField(blank=True, null=True)),
                ('first_image2', models.TextField(blank=True, null=True)),
                ('cat1', models.TextField(blank=True, null=True)),
                ('cat2', models.TextField(blank=True, null=True)),
                ('cat3', models.TextField(blank=True, null=True)),
                ('addr1', models.TextField(blank=True, null=True)),
                ('addr2', models.TextField(blank=True, null=True)),
                ('mapx', models.TextField(blank=True, null=True)),
                ('mapy', models.TextField(blank=True, null=True)),
                ('mlevel', models.TextField(blank=True, null=True)),
                ('area_code', models.TextField(blank=True, null=True)),
                ('sigungu_code', models.TextField(blank=True, null=True)),
            ],
            options={
                'db_table': 'place_common',
            },
        ),
        migrations.CreateModel(
            name='CultureDetail',
            fields=[
                ('common_no', models.ForeignKey(db_column='common_no', on_delete=django.db.models.deletion.CASCADE, primary_key=True, serialize=False, to='recommend.placecommon')),
                ('chk_creditcard', models.TextField(blank=True, null=True)),
                ('chk_pet', models.TextField(blank=True, null=True)),
                ('parking', models.TextField(blank=True, null=True)),
                ('rest_date', models.TextField(blank=True, null=True)),
                ('use_fee', models.TextField(blank=True, null=True)),
                ('use_time', models.TextField(blank=True, null=True)),
                ('scale', models.TextField(blank=True, null=True)),
                ('spend_time', models.TextField(blank=True, null=True)),
            ],
            options={
                'db_table': 'culture_detail',
            },
        ),
        migrations.CreateModel(
            name='FestivalDetail',
            fields=[
                ('common_no', models.ForeignKey(db_column='common_no', on_delete=django.db.models.deletion.CASCADE, primary_key=True, serialize=False, to='recommend.placecommon')),
                ('start_date', models.TextField(blank=True, null=True)),
                ('end_date', models.TextField(blank=True, null=True)),
                ('place', models.TextField(blank=True, null=True)),
                ('festival_grade', models.TextField(blank=True, null=True)),
                ('place_info', models.TextField(blank=True, null=True)),
                ('program', models.TextField(blank=True, null=True)),
                ('play_time', models.TextField(blank=True, null=True)),
                ('spend_time', models.TextField(blank=True, null=True)),
                ('use_time', models.TextField(blank=True, null=True)),
            ],
            options={
                'db_table': 'festival_detail',
            },
        ),
        migrations.CreateModel(
            name='LeisureDetail',
            fields=[
                ('common_no', models.ForeignKey(db_column='common_no', on_delete=django.db.models.deletion.CASCADE, primary_key=True, serialize=False, to='recommend.placecommon')),
                ('accom_count', models.TextField(blank=True, null=True)),
                ('chk_creditcard', models.TextField(blank=True, null=True)),
                ('chk_pet', models.TextField(blank=True, null=True)),
                ('info_center', models.TextField(blank=True, null=True)),
                ('open_period', models.TextField(blank=True, null=True)),
                ('parking', models.TextField(blank=True, null=True)),
                ('use_time', models.TextField(blank=True, null=True)),
            ],
            options={
                'db_table': 'leisure_detail',
            },
        ),
        migrations.CreateModel(
            name='RestaurantDetail',
            fields=[
                ('common_no', models.ForeignKey(db_column='common_no', on_delete=django.db.models.deletion.CASCADE, primary_key=True, serialize=False, to='recommend.placecommon')),
                ('chk_creditcard', models.TextField(blank=True, null=True)),
                ('info_center', models.TextField(blank=True, null=True)),
                ('first_menu', models.TextField(blank=True, null=True)),
                ('open_date', models.TextField(blank=True, null=True)),
                ('open_time', models.TextField(blank=True, null=True)),
                ('packing', models.TextField(blank=True, null=True)),
                ('parking', models.TextField(blank=True, null=True)),
                ('reservation', models.TextField(blank=True, null=True)),
                ('rest_date', models.TextField(blank=True, null=True)),
                ('scale', models.TextField(blank=True, null=True)),
                ('seat', models.TextField(blank=True, null=True)),
                ('smoking', models.TextField(blank=True, null=True)),
                ('treat_menu', models.TextField(blank=True, null=True)),
            ],
            options={
                'db_table': 'restaurant_detail',
            },
        ),
        migrations.CreateModel(
            name='ShoppingDetail',
            fields=[
                ('common_no', models.ForeignKey(db_column='common_no', on_delete=django.db.models.deletion.CASCADE, primary_key=True, serialize=False, to='recommend.placecommon')),
                ('chk_creditcard', models.TextField(blank=True, null=True)),
                ('chk_pet', models.TextField(blank=True, null=True)),
                ('culture_center', models.TextField(blank=True, null=True)),
                ('fair_day', models.TextField(blank=True, null=True)),
                ('info_center', models.TextField(blank=True, null=True)),
                ('open_date', models.TextField(blank=True, null=True)),
                ('open_time', models.TextField(blank=True, null=True)),
                ('parking', models.TextField(blank=True, null=True)),
                ('rest_date', models.TextField(blank=True, null=True)),
                ('rest_room', models.TextField(blank=True, null=True)),
                ('sale_item', models.TextField(blank=True, null=True)),
                ('sale_item_cost', models.TextField(blank=True, null=True)),
                ('scale', models.TextField(blank=True, null=True)),
                ('shop_guide', models.TextField(blank=True, null=True)),
            ],
            options={
                'db_table': 'shopping_detail',
            },
        ),
        migrations.CreateModel(
            name='TouristSpotDetail',
            fields=[
                ('common_no', models.ForeignKey(db_column='common_no', on_delete=django.db.models.deletion.CASCADE, primary_key=True, serialize=False, to='recommend.placecommon')),
                ('accom_count', models.TextField(blank=True, null=True)),
                ('chk_creditcard', models.TextField(blank=True, null=True)),
                ('chk_pet', models.TextField(blank=True, null=True)),
                ('heritage1', models.TextField(blank=True, null=True)),
                ('heritage2', models.TextField(blank=True, null=True)),
                ('heritage3', models.TextField(blank=True, null=True)),
                ('open_date', models.TextField(blank=True, null=True)),
                ('parking', models.TextField(blank=True, null=True)),
                ('rest_date', models.TextField(blank=True, null=True)),
                ('use_season', models.TextField(blank=True, null=True)),
                ('use_time', models.TextField(blank=True, null=True)),
            ],
            options={
                'db_table': 'tourist_spot_detail',
            },
        ),
    ]
