package dataprovider;

import org.testng.annotations.DataProvider;
import pages.project.Column;

public class QuestionTypeColumns {
    @DataProvider
    public Object[][] getComboBoxForRadioButtonColumns() {
        return new Object[][]{
                {Column.NPS_SINGLE},
                {Column.NPS_ROW},
                {Column.SINGLE_SELECT_RADIO_ROW},
                {Column.SINGLE_SELECT_RADIO_COLUMN},
                {Column.SINGLE_SELECT_BUTTON_ROW},
                {Column.SINGLE_SELECT_BUTTON_COLUMN},
                {Column.SINGLE_SELECT_DROPDOWN_SINGLE},
                {Column.SINGLE_SELECT_DROPDOWN_ROW},
                {Column.SINGLE_SELECT_DROPDOWN_COLUMN},
        };
    }

    @DataProvider
    public Object[][] getComboBoxForCheckboxColumns() {
        return new Object[][]{
                {Column.MULTI_SELECT_CHECKBOX_ROW},
                {Column.MULTI_SELECT_CHECKBOX_COLUMN},
                {Column.MULTI_SELECT_BUTTON_ROW},
                {Column.MULTI_SELECT_BUTTON_COLUMN},
                {Column.MULTI_SELECT_DROPDOWN_SINGLE},
                {Column.MULTI_SELECT_DROPDOWN_ROW},
                {Column.MULTI_SELECT_DROPDOWN_COLUMN},

        };
    }

    @DataProvider
    public Object[][] getNumericComboBoxForInputColumns() {
        return new Object[][]{
                {Column.NUMERIC_NUMBER_TEXTBOX_SINGLE},
                {Column.NUMERIC_NUMBER_TEXTBOX_ROW},
                {Column.NUMERIC_NUMBER_TEXTBOX_COLUMN},
                {Column.NUMERIC_CURRENCY_TEXTBOX_SINGLE},
                {Column.NUMERIC_CURRENCY_TEXTBOX_ROW},
                {Column.NUMERIC_CURRENCY_TEXTBOX_COLUMN},
                {Column.NUMERIC_DECIMAL_TEXTBOX_SINGLE},
                {Column.NUMERIC_DECIMAL_TEXTBOX_ROW},
                {Column.NUMERIC_DECIMAL_TEXTBOX_COLUMN},
                {Column.AUTOSUM_NUMBER_TEXTBOX_ROW},
                {Column.AUTOSUM_NUMBER_TEXTBOX_COLUMN},
                {Column.AUTOSUM_CURRENCY_TEXTBOX_ROW},
                {Column.AUTOSUM_CURRENCY_TEXTBOX_COLUMN},
                {Column.AUTOSUM_DECIMAL_TEXTBOX_ROW},
                {Column.AUTOSUM_DECIMAL_TEXTBOX_COLUMN},
        };
    }

    @DataProvider
    public Object[][] getNumericComboBoxForUIValidation() {
        return new Object[][]{
                {Column.NUMERIC_NUMBER_TEXTBOX_SINGLE},
                {Column.NUMERIC_NUMBER_TEXTBOX_ROW},
                {Column.NUMERIC_NUMBER_TEXTBOX_COLUMN},
                {Column.NUMERIC_CURRENCY_TEXTBOX_SINGLE},
                {Column.NUMERIC_CURRENCY_TEXTBOX_ROW},
                {Column.NUMERIC_CURRENCY_TEXTBOX_COLUMN},
                {Column.NUMERIC_DECIMAL_TEXTBOX_SINGLE},
                {Column.NUMERIC_DECIMAL_TEXTBOX_ROW},
                {Column.NUMERIC_DECIMAL_TEXTBOX_COLUMN},
                {Column.AUTOSUM_NUMBER_TEXTBOX_ROW},
                {Column.AUTOSUM_NUMBER_TEXTBOX_COLUMN},
                {Column.AUTOSUM_CURRENCY_TEXTBOX_ROW},
                {Column.AUTOSUM_CURRENCY_TEXTBOX_COLUMN},
                {Column.AUTOSUM_DECIMAL_TEXTBOX_ROW},
                {Column.AUTOSUM_DECIMAL_TEXTBOX_COLUMN},};
    }
    @DataProvider
    public Object[][] getEssayComboBoxForInputColumns() {
        return new Object[][]{
                {Column.ESSAY_TEXTAREA_SINGLE},
                {Column.ESSAY_TEXTAREA_ROW},
                {Column.ESSAY_TEXTAREA_COLUMN},
                {Column.ESSAY_TEXTBOX_SINGLE},
                {Column.ESSAY_TEXTBOX_ROW},
                {Column.ESSAY_TEXBOX_COLUMN},
        };
    }

    @DataProvider
    public Object[][] getSliderScaleQuestions() {
        return new Object[][]{
                {Column.SLIDER_SCALE_NUMERIC_NUMBER_SINGLE},
                {Column.SLIDER_SCALE_NUMERIC_NUMBER_ROW},
                {Column.SLIDER_SCALE_NUMERIC_CURRENCY_SINGLE},
                {Column.SLIDER_SCALE_NUMERIC_CURRENCY_ROW},
                {Column.SLIDER_SCALE_NUMERIC_DECIMAL_SINGLE},
                {Column.SLIDER_SCALE_NUMERIC_DECIMAL_ROW},

        };
    }


    @DataProvider
    public Object[][] getComboBoxForRankedQuestions() {
        return new Object[][]{
                {Column.RANK_ORDINAL_DRAG_LINEAR_ROW},
                {Column.RANK_ORDINAL_ASSIGN_LINEAR_ROW},
                {Column.RANK_ORDINAL_DRAG_ZONE_ROW},
        };
    }

    @DataProvider
    public Object[][] getOtherSpecifyAnswers() {
        return new Object[][]{
                {Column.Q1_R3_OTHER},
        };
    }

    @DataProvider()
    public Object[][] getOnlyRadioButtonColumns() {
        return new Object[][]{
                {Column.NPS_SINGLE},
                {Column.NPS_ROW},
                {Column.SINGLE_SELECT_RADIO_ROW},
                {Column.SINGLE_SELECT_RADIO_COLUMN},
                {Column.SINGLE_SELECT_BUTTON_ROW},
                {Column.SINGLE_SELECT_BUTTON_COLUMN},
                {Column.SINGLE_SELECT_DROPDOWN_SINGLE},
                {Column.SINGLE_SELECT_DROPDOWN_ROW},
                {Column.SINGLE_SELECT_DROPDOWN_COLUMN},
                {Column.STAR_SCALE_NUMERIC_NUMBER_SINGLE},
                {Column.STAR_SCALE_NUMERIC_NUMBER_ROW},
                {Column.RANK_MAXIMUM_DIFFERENCE_SCALE},
                {Column.DRAGDROP_SINGLE_ASSIGNMENT_BUCKET},
                {Column.DRAGDROP_SINGLE_ASSIGNMENT_BUTTON},
                {Column.SINGLE_SELECT_BUTTON_GRID},
                {Column.SINGLE_SELECT_DROPDOWN_GRID},
                {Column.SINGLE_SELECT_RADIO_BUTTON_GRID},

        };
    }

    @DataProvider
    public Object[][] getOnlyVirtualRadioButtonColumns() {
        return new Object[][]{
                {Column.SINGLE_SELECT_RADIO_ROW},
                {Column.SINGLE_SELECT_RADIO_COLUMN},
                {Column.SINGLE_SELECT_BUTTON_ROW},
                {Column.SINGLE_SELECT_BUTTON_COLUMN},
                {Column.SINGLE_SELECT_DROPDOWN_SINGLE},
                {Column.SINGLE_SELECT_DROPDOWN_ROW},
                {Column.SINGLE_SELECT_DROPDOWN_COLUMN},
                {Column.SINGLE_SELECT_RADIO_BUTTON_GRID},
                {Column.SINGLE_SELECT_BUTTON_GRID},
                {Column.SINGLE_SELECT_DROPDOWN_GRID},
                {Column.RANK_MAXIMUM_DIFFERENCE_SCALE},
                {Column.DRAGDROP_SINGLE_ASSIGNMENT_BUCKET},
                {Column.DRAGDROP_SINGLE_ASSIGNMENT_BUTTON},
        };
    }


    @DataProvider
    public Object[][] getOnlyCheckboxColumns() {
        return new Object[][]{
                {Column.MULTI_SELECT_CHECKBOX_ROW},
                {Column.MULTI_SELECT_CHECKBOX_COLUMN},
                {Column.MULTI_SELECT_BUTTON_ROW},
                {Column.MULTI_SELECT_BUTTON_COLUMN},
                {Column.MULTI_SELECT_DROPDOWN_SINGLE},
                {Column.MULTI_SELECT_DROPDOWN_ROW},
                {Column.MULTI_SELECT_DROPDOWN_COLUMN},
                {Column.MULTI_SELECT_CHECKBOX_GRID},
                {Column.MULTI_SELECT_BUTTON_GRID},
                {Column.MULTI_SELECT_DROPDOWN_GRID},

        };
    }
    @DataProvider
    public Object[][] getOnlyCheckboxColumnsForUIValidation() {
        return new Object[][]{
                {Column.MULTI_SELECT_CHECKBOX_ROW},
                {Column.MULTI_SELECT_CHECKBOX_COLUMN},
                {Column.MULTI_SELECT_BUTTON_ROW},
                {Column.MULTI_SELECT_BUTTON_COLUMN},
                {Column.MULTI_SELECT_DROPDOWN_SINGLE},
                {Column.MULTI_SELECT_DROPDOWN_ROW},
                {Column.MULTI_SELECT_DROPDOWN_COLUMN},

        };
    }
    @DataProvider
    public Object[][] getOnlyCheckboxGridColumns() {
        return new Object[][]{
                {Column.MULTI_SELECT_CHECKBOX_GRID},
                {Column.MULTI_SELECT_BUTTON_GRID},
                {Column.MULTI_SELECT_DROPDOWN_GRID},

        };
    }

    @DataProvider
    public Object[][] getOnlyNumericColumns() {
        return new Object[][]{
                {Column.NUMERIC_NUMBER_TEXTBOX_SINGLE},
                {Column.NUMERIC_NUMBER_TEXTBOX_ROW},
                {Column.NUMERIC_NUMBER_TEXTBOX_COLUMN},
                {Column.NUMERIC_CURRENCY_TEXTBOX_SINGLE},
                {Column.NUMERIC_CURRENCY_TEXTBOX_ROW},
                {Column.NUMERIC_CURRENCY_TEXTBOX_COLUMN},
                {Column.NUMERIC_DECIMAL_TEXTBOX_SINGLE},
                {Column.NUMERIC_DECIMAL_TEXTBOX_ROW},
                {Column.NUMERIC_DECIMAL_TEXTBOX_COLUMN},
                {Column.AUTOSUM_NUMBER_TEXTBOX_ROW},
                {Column.AUTOSUM_NUMBER_TEXTBOX_COLUMN},
                {Column.AUTOSUM_CURRENCY_TEXTBOX_ROW},
                {Column.AUTOSUM_CURRENCY_TEXTBOX_COLUMN},
                {Column.AUTOSUM_DECIMAL_TEXTBOX_ROW},
                {Column.AUTOSUM_DECIMAL_TEXTBOX_COLUMN},
                {Column.RANK_ORDINAL_DRAG_LINEAR_ROW},
                {Column.RANK_ORDINAL_ASSIGN_LINEAR_ROW},
                {Column.RANK_ORDINAL_DRAG_ZONE_ROW},

        };
    }
    @DataProvider
    public Object[][] getOnlyNumericGridColumns() {
        return new Object[][]{
                {Column.NUMERIC_NUMBER_TEXTBOX_GRID},
                {Column.NUMERIC_CURRENCY_TEXTBOX_GRID},
                {Column.NUMERIC_DECIMAL_TEXTBOX_GRID},
                {Column.AUTOSUM_NUMBER_TEXTBOX_GRID},
                {Column.AUTOSUM_DECIMAL_TEXTBOX_GRID},
                {Column.AUTOSUM_CURRENCY_TEXTBOX_GRID},
        };

    }
    @DataProvider
    public Object[][] getOnlySliderColumns() {
        return new Object[][]{
                {Column.SLIDER_SCALE_NUMERIC_NUMBER_SINGLE},
                {Column.SLIDER_SCALE_NUMERIC_NUMBER_ROW},
                {Column.SLIDER_SCALE_NUMERIC_CURRENCY_SINGLE},
                {Column.SLIDER_SCALE_NUMERIC_CURRENCY_ROW},
                {Column.SLIDER_SCALE_NUMERIC_DECIMAL_SINGLE},
                {Column.SLIDER_SCALE_NUMERIC_DECIMAL_ROW},
        };

    }


    @DataProvider
    public Object[][] getOnlyEssayColumns() {
        return new Object[][]{
                {Column.ESSAY_TEXTAREA_SINGLE},
                {Column.ESSAY_TEXTAREA_ROW},
                {Column.ESSAY_TEXTAREA_COLUMN},
                {Column.ESSAY_TEXTBOX_SINGLE},
                {Column.ESSAY_TEXTBOX_ROW},
                {Column.ESSAY_TEXBOX_COLUMN},
                {Column.ESSAY_TEXT_AREA_GRID},
                {Column.ESSAY_TEXTBOX_GRID},
        };

    }

    @DataProvider
    public Object[][] getComboBoxWithVirtualCheckBoxRowChoices() {
        return new Object[][]{
                {Column.MULTI_SELECT_CHECKBOX_ROW},

        };
    }

    @DataProvider
    public Object[][] getComboBoxWithVirtualCheckBoxColumnChoices() {
        return new Object[][]{
                {Column.MULTI_SELECT_CHECKBOX_COLUMN},


        };
    }

    @DataProvider
    public Object[][] getStatusColumn() {
        return new Object[][]{
                {Column.STATUS},
        };
    }

     @DataProvider
    public Object[][] getMandatorySurveyColumns() {
        return new Object[][]{
                {Column.RESPONDENT_Id
                },
        };
    }

    @DataProvider
    public Object[][] getOnlyMultiSelectCheckboxRow() {
        return new Object[][]{
                {Column.MULTI_SELECT_CHECKBOX_ROW},


        };
    }
    @DataProvider
    public Object[][] getOnlyNPSRadioButton() {
        return new Object[][]{
                {Column.NPS_SINGLE},


        };
    }

    @DataProvider
    public Object[][] getOnlyEssayTextAreaSingle() {
        return new Object[][]{
                {Column.ESSAY_TEXTAREA_SINGLE},


        };
    }

    @DataProvider
    public Object[][] getOnlyNumericNumberTextBoxSingle() {
        return new Object[][]{
                {Column.NUMERIC_NUMBER_TEXTBOX_SINGLE},


        };
    }

    @DataProvider
    public Object[][] getOnlyNPSSingle() {
        return new Object[][]{
                {Column.NPS_SINGLE},


        };
    }
}